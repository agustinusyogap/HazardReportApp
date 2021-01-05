package com.ksm.hazardreportapp.controllers;

import com.ksm.hazardreportapp.entities.Actions;
import com.ksm.hazardreportapp.entities.Reports;
import com.ksm.hazardreportapp.providers.CustomUser;
import com.ksm.hazardreportapp.services.ActionService;
import com.ksm.hazardreportapp.services.MailingService;
import com.ksm.hazardreportapp.services.ReportService;
import com.ksm.hazardreportapp.utils.MailingTemplate;
import com.pusher.rest.Pusher;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kelvi
 */
@Controller
public class ActionController {

    @Autowired
    ActionService actionService;

    @Autowired
    ReportService reportService;

    @Autowired
    MailingService mailingService;

    @GetMapping("/admin/report/{id}/action/new")
    public String addNewAction(@PathVariable("id") int id, Model model) {
        int priority = reportService.getById(id).getPriority().getId();

        if (priority == 3) {
            model.addAttribute("actionType", 1);
        } else {
            model.addAttribute("actionType", 2);
        }

        model.addAttribute("reportId", id);
        model.addAttribute("newAction", new Actions());
        model.addAttribute("title", "Create Action");
        return "createAction";
    }

    @GetMapping("/admin/report/{id}/action/view/{actionId}")
    public String viewAction(@PathVariable("actionId") int actionId, Model model) {
        model.addAttribute("action", actionService.getById(actionId));
        model.addAttribute("title", "View Action");
        return "viewAction";
    }

    @PostMapping("/admin/report/{id}/action/new")
    public String performAddNewAction(@PathVariable("id") int id, Actions actions) {

        Pusher pusher = new Pusher("1121252", "75b839e2c030a656a41c", "f17af836b1f4bcfeeffe");
        pusher.setCluster("ap1");
        pusher.setEncrypted(true);
        pusher.trigger("my-channel", "updateNotif", Collections.singletonMap("message", "success"));

        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Reports reports = reportService.getById(id);

        int priority = reports.getPriority().getId();

        if (priority == 3) {
            if (user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("FW"))) {
                actions.setReportProgress(reportService.updateStatus(3, id));
                actions.setReportProgress(reportService.updateStatus(4, id));
                try {
                    mailingService.sendEmail(
                            reports.getOriginator().getEmail(),
                            MailingTemplate.notifyProgress(
                                    new Date(System.currentTimeMillis()),
                                    "The floor warden has responded and taken temporary action on your report, and the report will now be forwarded to HSE",
                                    reports.getId()),
                            "Report Progress update #Report" + id
                    );
                } catch (Exception ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("HSE"))) {
                actions.setReportProgress(reportService.updateStatus(5, id));
                actions.setReportProgress(reportService.updateStatus(6, id));
                try {
                    mailingService.sendEmail(
                            reports.getOriginator().getEmail(),
                            MailingTemplate.notifyProgress(
                                    new Date(System.currentTimeMillis()),
                                    "Your report has been responded to by HSE, and the report will be closed",
                                    reports.getId()),
                            "Report Progress update #Report" + id
                    );
                    mailingService.sendEmail(
                            reports.getOriginator().getEmail(),
                            MailingTemplate.notifyProgressEnd(
                                    new Date(System.currentTimeMillis()),
                                    "thank you for reporting, and now the problem has been handled and resolved by HSE",
                                    reports.getId()),
                            "Report Closed #Report" + id
                    );
                } catch (Exception ex) {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            actions.setReportProgress(reportService.updateStatus(3, id));
            actions.setReportProgress(reportService.updateStatus(6, id));
            try {
                mailingService.sendEmail(
                        reports.getOriginator().getEmail(),
                        MailingTemplate.notifyProgress(
                                new Date(System.currentTimeMillis()),
                                "Your report has been responded to by Floor warden, and the report will be closed",
                                reports.getId()),
                        "Report Progress update #Report" + id
                );
                mailingService.sendEmail(
                        reports.getOriginator().getEmail(),
                        MailingTemplate.notifyProgressEnd(
                                new Date(System.currentTimeMillis()),
                                "thank you for reporting, and now the problem has been handled and resolved by Floor warden",
                                reports.getId()),
                        "Report Closed #Report" + id
                );
            } catch (Exception ex) {
                Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        actions.setDate(date);

        actionService.save(actions);
        return "redirect:/admin/report/details/{id}?res=added";
    }

}
