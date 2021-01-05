/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.repositories;

import com.ksm.hazardreportapp.entities.Actions;
import com.ksm.hazardreportapp.entities.ReportProgresses;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author YOGA
 */
@Repository
public interface ActionRepository extends JpaRepository<Actions, Integer> {

    @Query("Select a from Actions a where a.reportProgress IN (:reportProgress)")
    public List<Actions> FindByReportProgressIn(@Param("reportProgress") List<ReportProgresses> reportProgress);
}
