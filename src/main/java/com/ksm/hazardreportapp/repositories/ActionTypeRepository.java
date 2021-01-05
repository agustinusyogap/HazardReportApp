/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.repositories;

import com.ksm.hazardreportapp.entities.ActionTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YOGA
 */
@Repository
public interface ActionTypeRepository extends JpaRepository<ActionTypes, Integer> {
    
}
