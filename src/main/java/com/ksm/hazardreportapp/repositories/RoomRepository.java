/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.repositories;

import com.ksm.hazardreportapp.entities.Floors;
import com.ksm.hazardreportapp.entities.Rooms;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YOGA
 */
@Repository
public interface RoomRepository extends JpaRepository<Rooms, Integer> {

    public List<Rooms> findByFloorIn(@Param("floor") List<Floors> floor);
}
