/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.ReportProgresses;
import com.ksm.hazardreportapp.repositories.ReportProgressRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boona
 */
@Service
public class ReportProgressService {

    ReportProgressRepository repository;

    public List<ReportProgresses> getAll() {
        return repository.findAll();
    }

    public ReportProgresses getById(int id) {
        return repository.findById(id).get();
    }

    public ReportProgresses save(ReportProgresses reportProgresses) {
        return repository.save(reportProgresses);
    }

    public void delete(int id) {
        repository.delete(new ReportProgresses(id));
    }
}
