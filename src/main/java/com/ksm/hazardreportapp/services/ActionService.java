/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.Actions;
import com.ksm.hazardreportapp.repositories.ActionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YOGA
 */
@Service
public class ActionService {

    @Autowired
    ActionRepository repository;

    @Autowired
    ReportProgressService reportProgressService;

    @Autowired
    ReportService reportService;

    public List<Actions> getAll() {
        return repository.findAll();
    }

    public List<Actions> getByReportProgress(int id) {
        return repository.FindByReportProgressIn(reportService.getById(id).getReportProgressesList());
    }

    public Actions getById(Integer id) {
        return repository.findById(id).get();
    }

    public boolean save(Actions actions) {
        try {
            repository.save(actions);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean delete(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Actions actions) {
        try {
            repository.save(actions);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
