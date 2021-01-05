/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.Priorities;
import com.ksm.hazardreportapp.repositories.PrioritiesRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YOGA
 */
@Service
public class PriorityService {

    @Autowired
    PrioritiesRepository repository;

    public List<Priorities> getAll() {
        return repository.findAll();
    }

    public List<Priorities> getPriorities() {
        List<Integer> notIn = new ArrayList<>();
        notIn.add(4);
        return repository.findByIdNotIn(notIn);
    }

    public Priorities getById(Integer id) {
        return repository.findById(id).get();
    }

    public boolean save(Priorities priority) {
        try {
            repository.save(priority);
            return true;
        } catch (Exception e) {
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

    public boolean update(Priorities priority) {
        try {
            repository.save(priority);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
