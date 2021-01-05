/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.controllers;

import com.ksm.hazardreportapp.entities.Floors;
import com.ksm.hazardreportapp.entities.Rooms;
import com.ksm.hazardreportapp.providers.CustomUser;
import com.ksm.hazardreportapp.services.FloorService;
import com.ksm.hazardreportapp.services.RoleService;
import com.ksm.hazardreportapp.services.RoomService;
import com.ksm.hazardreportapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author kelvi
 */
@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    FloorService floorService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;
    int floor = 1;

    // Routes for admin as HSE
    @GetMapping("/admin/floor")
    public String manageFloor(Model model) {
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("floorWarden", roleService.getById(2).getUsersList());
        model.addAttribute("title", "Manage Floor");
        return "manageFloor";
    }

    @PostMapping("/admin/floor")
    public String performAddFloor(Floors floors) {
        floorService.save(floors);
        return "redirect:/admin/floor?res=addup";
    }

    // Routes for admin as FLadmin
    @GetMapping("/fladmin/room")
    public String manageRoom(Model model) {

        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = user.getId();

        model.addAttribute("rooms", roomService.getAllByFloor(id));
        model.addAttribute("floors", userService.getById(id).getFloorsList());
        model.addAttribute("newRoom", new Rooms());
        model.addAttribute("title", "Manage Room");
        return "manageRoom";
    }

    @PostMapping("/fladmin/room")
    public String performAddRoom(Rooms rooms) {
        roomService.save(rooms);
        return "redirect:/fladmin/room?res=addup";
    }

    @GetMapping("/fladmin/room/perform_delete/{id}")
    public String performDeleteRoom(@PathVariable("id") int id) {
        roomService.delete(id);
        return "redirect:/fladmin/room?res=deleted";
    }

}
