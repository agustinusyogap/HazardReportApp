/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.repositories;

import com.ksm.hazardreportapp.entities.Roles;
import com.ksm.hazardreportapp.entities.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author YOGA
 */
@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    public Users findByUsername(@Param("username") String username);

    public Users findByEmail(@Param("email") String email);

    public List<Users> findByRoles(@Param("roles") Roles roles);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Users u set u.id =:id where u.email =:email")
    public int syncLocalUserIdAndServerUserId(@Param("id") String id, @Param("email") String email);
}
