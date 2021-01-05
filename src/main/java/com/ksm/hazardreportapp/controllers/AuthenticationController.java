/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.controllers;

import com.ksm.hazardreportapp.entities.rest.LoginInput;
import com.ksm.hazardreportapp.services.rest.LoginService;
import com.ksm.hazardreportapp.utils.Auth;
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
public class AuthenticationController {

    @Autowired
    LoginService loginService;

    @GetMapping("/auth/login")
    public String loginPage(Model model) {

        if (Auth.isLogged()) {
            return "redirect:/admin";
        }

        System.out.println("Logged in");
        model.addAttribute("title", "Sign In");
        model.addAttribute("user", new LoginInput());
        return "login";
    }

    @PostMapping("/auth/perform_login")
    public String loginPerform(LoginInput loginInput) {
        System.out.println(loginService.login(loginInput));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutPerform() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/";
    }

}
