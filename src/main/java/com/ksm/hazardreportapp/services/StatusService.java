/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.Statuses;
import com.ksm.hazardreportapp.repositories.StatusRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boona
 */
@Service
public class StatusService {

    StatusRepository repository;

    public List<Statuses> getAll() {
        return repository.findAll();
    }

    public Statuses getById(int id) {
        return repository.findById(id).get();
    }

    public Statuses save(Statuses statuses) {
        return repository.save(statuses);
    }

    public void delete(int id) {
        repository.delete(new Statuses(id));
    }
}
