/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.controllers;

import com.ksm.hazardreportapp.entities.rest.profile.*;
import com.ksm.hazardreportapp.providers.CustomUser;
import com.ksm.hazardreportapp.services.UserService;
import com.ksm.hazardreportapp.services.rest.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author kelvi
 */
@Controller
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String index(Model model) {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = user.getId();

        model.addAttribute("basic", profileService.getProfileBasic(id));
        model.addAttribute("address", profileService.getProfileAddress(id));
        model.addAttribute("contact", profileService.getProfileContact(id));
        model.addAttribute("occupation", profileService.getProfileOccupation(id));
        model.addAttribute("education", profileService.getProfileEducation(id));
        model.addAttribute("majors", userService.getMajors());
        model.addAttribute("universities", userService.getUniversities());

        model.addAttribute("title", "My Profile");
        return "profile/index";
    }

    @PostMapping("/profile/basic/perform_update")
    public String profileBasicPerformUpdate(Basic basic) {
        profileService.saveProfileBasic(basic);
        return "redirect:/profile?res=addup";
    }

    @PostMapping("/profile/address/perform_update")
    public String profileAddressPerformUpdate(Address address) {
        profileService.saveProfileAddress(address);
        return "redirect:/profile?res=addup";
    }

    @PostMapping("/profile/contact/perform_update")
    public String profileContactPerformUpdate(Contact contact) {
        profileService.saveProfileContact(contact);
        return "redirect:/profile?res=addup";
    }

    @PostMapping("/profile/currentoccupation/perform_update")
    public String profileProfilePerformUpdate(CurrentOccupation currentOccupation) {
        profileService.saveProfileOccupation(currentOccupation);
        return "redirect:/profile?res=addup";
    }

    @PostMapping("/profile/education/perform_update")
    public String profileEducationPerformUpdate(Education education) {
        profileService.saveProfileEducation(education);
        return "redirect:/profile?res=addup";
    }

}
