/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.Floors;
import com.ksm.hazardreportapp.repositories.FloorRepository;
import com.ksm.hazardreportapp.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YOGA
 */
@Service
public class FloorService {

    @Autowired
    FloorRepository repository;

    @Autowired
    UserRepository userRepository;

    public List<Floors> getAll() {
        return repository.findAll();
    }

    public List<Floors> getAllByUser(String user) {
        return repository.findByAdmin(userRepository.findById(user).get());
    }

    public Floors getById(Integer id) {
        return repository.findById(id).get();
    }

    public boolean save(Floors floors) {
        try {
            repository.save(floors);
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

    public boolean update(Floors floors) {
        try {
            repository.save(floors);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
