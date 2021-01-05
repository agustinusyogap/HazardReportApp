/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.entities.rest.profile;

import lombok.Data;

/**
 *
 * @author kelvi
 */
@Data
public class Address {

    private String id;
    private String street1;
    private String street2;
    private String city;
    private String province;
    private String zipCode;
}
