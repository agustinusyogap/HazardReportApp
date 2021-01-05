/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.entities.rest;

import lombok.Data;

/**
 *
 * @author Kelvi
 */
@Data
public class RegisterInput {

    private String birthDate;
    private String currentCompany;
    private String degree;
    private String email;
    private String gender;
    private String job;
    private String major;
    private String name;
    private String password;
    private String phone;
    private String status;
    private String university;
    private String username;

}
