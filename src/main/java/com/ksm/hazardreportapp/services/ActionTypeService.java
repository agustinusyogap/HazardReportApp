/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.ActionTypes;
import com.ksm.hazardreportapp.repositories.ActionTypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author YOGA
 */
@Service
public class ActionTypeService {

    ActionTypeRepository repository;

    public List<ActionTypes> getAll() {
        return repository.findAll();
    }

    public ActionTypes getById(Integer id) {
        return repository.findById(id).get();
    }

    public boolean save(ActionTypes actiontypes) {
        try {
            repository.save(actiontypes);
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

    public boolean update(ActionTypes actiontypes) {
        try {
            repository.save(actiontypes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
