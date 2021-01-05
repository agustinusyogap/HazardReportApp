/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.Floors;
import com.ksm.hazardreportapp.entities.ReportProgresses;
import com.ksm.hazardreportapp.entities.Reports;
import com.ksm.hazardreportapp.entities.Rooms;
import com.ksm.hazardreportapp.entities.Statuses;
import com.ksm.hazardreportapp.entities.Users;
import com.ksm.hazardreportapp.repositories.ReportRepository;
import com.ksm.hazardreportapp.repositories.StatusRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boona
 */
@Service
public class ReportService {

    @Autowired
    ReportRepository repository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    PriorityService priorityService;

    @Autowired
    UserService userService;

    @Autowired
    FloorService floorService;

    public List<Reports> getAll() {
        return repository.findAll();
    }

    public List<Reports> getAllByUser(String id) {

        //System.out.println("User ID from Service = " + id);
        Users user = userService.getById(id);

        // System.out.println("Floor = " + user.getFloorsList());
        List<Floors> floors = user.getFloorsList();
        List<Rooms> finalRooms = new ArrayList<>();

        floors.forEach((floor) -> {
            List<Rooms> rooms = floor.getRoomsList();
            //System.out.println("Room in floor = " + floor.getRoomsList());
            rooms.forEach((room) -> {
                finalRooms.add(room);
                //System.out.println("Each room : " + room.getName() + " in floor : " + floor.getName());
            });
        });
        return repository.findByRoomIn(finalRooms);

    }

    public List<Reports> getNews() {
        List<Reports> news = repository.findTop5ByCurrentStatusOrderByIdDesc(statusRepository.findById(6).get());
        return news;
    }

    public List<Reports> getReportFiltered(String id, String type) {

        Users user = userService.getById(id);

        List<Floors> floors = user.getFloorsList();
        List<Statuses> currentStatus = new ArrayList<>();

        List<Rooms> finalRooms = new ArrayList<>();

        floors.forEach((floor) -> {
            List<Rooms> rooms = floor.getRoomsList();
            rooms.forEach((room) -> {
                finalRooms.add(room);
            });
        });

        if (type.equalsIgnoreCase("finished")) {
            currentStatus.add(statusRepository.findById(6).get());
        }

        if (type.equalsIgnoreCase("onProcess")) {
            currentStatus.add(statusRepository.findById(2).get());
            currentStatus.add(statusRepository.findById(3).get());
            currentStatus.add(statusRepository.findById(4).get());
            currentStatus.add(statusRepository.findById(5).get());
        }

        if (type.equalsIgnoreCase("new")) {
            currentStatus.add(statusRepository.findById(1).get());
        }

        if (type.equalsIgnoreCase("all")) {
            currentStatus.add(statusRepository.findById(1).get());
            currentStatus.add(statusRepository.findById(2).get());
            currentStatus.add(statusRepository.findById(3).get());
            currentStatus.add(statusRepository.findById(4).get());
            currentStatus.add(statusRepository.findById(5).get());
            currentStatus.add(statusRepository.findById(6).get());
        }

        return repository.findByRoomInAndCurrentStatusIn(finalRooms, currentStatus);
    }

    public List<Reports> getAllByOriginator(Users user) {
        return repository.findByOriginator(user);
    }

    public List<Reports> getFinished() {
        List<Statuses> currentStatus = new ArrayList<>();
        currentStatus.add(statusRepository.findById(6).get());

        return repository.findByCurrentStatusIn(currentStatus);
    }

    public List<Reports> getNew() {
        List<Statuses> currentStatus = new ArrayList<>();
        currentStatus.add(statusRepository.findById(1).get());

        return repository.findByCurrentStatusIn(currentStatus);
    }

    public List<Reports> getOnProcess() {
        List<Statuses> currentStatus = new ArrayList<>();
        currentStatus.add(statusRepository.findById(2).get());
        currentStatus.add(statusRepository.findById(3).get());
        currentStatus.add(statusRepository.findById(4).get());
        currentStatus.add(statusRepository.findById(5).get());

        return repository.findByCurrentStatusIn(currentStatus);
    }

    public List<Reports> getAllByCurrentStatus() {
        List<Statuses> currentStatus = new ArrayList<>();
        currentStatus.add(statusRepository.findById(4).get());
        currentStatus.add(statusRepository.findById(5).get());
        currentStatus.add(statusRepository.findById(6).get());

        return repository.findByPriorityAndCurrentStatusIn(priorityService.getById(3), currentStatus);
    }

    public Reports getById(int id) {
        return repository.findById(id).get();
    }

    public Reports save(Reports reports) {
        return repository.save(reports);
    }

    public void delete(int id) {
        repository.delete(new Reports(id));
    }

    public ReportProgresses updateStatus(int status, int id) {
        Reports reports = repository.findById(id).get();
        Statuses statuses = statusRepository.findById(status).get();
        reports.setCurrentStatus(statuses);
        repository.save(reports);

        List<ReportProgresses> reportProgresses = reports.getReportProgressesList();
        return reportProgresses.get(reportProgresses.size() - 1);
    }

    public boolean setPriority(int id, int priority) {
        Reports reports = repository.findById(id).get();
        reports.setPriority(priorityService.getById(priority));
        try {
            repository.save(reports);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
