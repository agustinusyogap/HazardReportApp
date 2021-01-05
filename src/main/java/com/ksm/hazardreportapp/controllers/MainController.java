/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.controllers;

import com.ksm.hazardreportapp.entities.Notifications;
import com.ksm.hazardreportapp.entities.Reports;
import com.ksm.hazardreportapp.providers.CustomUser;
import com.ksm.hazardreportapp.repositories.NotificationRepository;
import com.ksm.hazardreportapp.services.ImageStorageService;
import com.ksm.hazardreportapp.services.MailingService;
import com.ksm.hazardreportapp.services.NotificationService;
import com.ksm.hazardreportapp.services.ReportService;
import com.ksm.hazardreportapp.services.UserService;
import com.ksm.hazardreportapp.utils.GeneratePdfReport;
import com.ksm.hazardreportapp.utils.GenerateSheetReport;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kelvi
 */
@Controller
public class MainController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    ImageStorageService imageStorageService;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserService userService;

    @Autowired
    ReportService reportService;

    @Autowired
    MailingService mailingService;

    @GetMapping("")
    public String redirectToIndex() {
        return "redirect:/admin";
    }

    @GetMapping("/admin/notification")
    public String displayNotification(Model model) {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = user.getId();
        model.addAttribute("notifications", notificationService.getByUserId(id));
        return "notification";
    }

    // Rouotes for admin as HSE
    @GetMapping("/admin")
    public String index(Model model) {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String id = user.getId();
        System.out.println("USER ID : " + id + "");
        System.out.println("ROLES : " + user.getAuthorities());
        model.addAttribute("title", "Dashboard");

        int totalReport = 0, finishedReport = 0, newReport = 0, onProcessReport = 0;

        if (user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equalsIgnoreCase("FW"))) {
            totalReport = reportService.getReportFiltered(id, "all").size();
            finishedReport = reportService.getReportFiltered(id, "finished").size();
            newReport = reportService.getReportFiltered(id, "new").size();
            onProcessReport = reportService.getReportFiltered(id, "onProcess").size();
        }

        if (user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equalsIgnoreCase("HSE"))) {
            totalReport = reportService.getAll().size();
            finishedReport = reportService.getFinished().size();
            newReport = reportService.getNew().size();
            onProcessReport = reportService.getOnProcess().size();
        }

        model.addAttribute("totalReport", totalReport);
        model.addAttribute("finishedReport", finishedReport);
        model.addAttribute("newReport", newReport);
        model.addAttribute("onProcessReport", onProcessReport);

        model.addAttribute("newsReport", reportService.getNews());
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("notifications", notificationService.getByUserUnSeen(id, 5));

        return "dashboard";
    }

    // Other routes
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = imageStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/open/{id}")
    public String openNotification(@PathVariable int id, @RequestParam String url) {
        notificationService.readNotification(id);
        return "redirect:" + url;
    }

    @ResponseBody
    @GetMapping("api/get/notification")
    public List<Notifications> getNotification() {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = user.getId();
        return notificationService.getByUserUnSeen(id, 0);
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

    @RequestMapping(value = "/admin/generate/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        List<Reports> reports = reportService.getAll();
        ByteArrayInputStream bis = GeneratePdfReport.printReport(reports);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Reports_" + currentDateTime + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/admin/generate/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Reports_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Reports> listUsers = reportService.getAll();

        GenerateSheetReport excelExporter = new GenerateSheetReport(listUsers);

        excelExporter.export(response);
    }
}
