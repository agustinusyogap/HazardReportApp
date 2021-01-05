/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.repositories;

import com.ksm.hazardreportapp.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author YOGA
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer>{
    public List<Roles> findByIdIn(@Param("id") List<Integer> id);
}
