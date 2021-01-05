/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services.rest;

import com.ksm.hazardreportapp.entities.rest.profile.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kelvi
 */
@Service
public class ProfileService {

    @Autowired
    RestTemplate restTemplate;

    private final String uri = "http://116.254.101.228:8080/ma_test/";

    public Basic getProfileBasic(String id) {

        Basic output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/basic/{id}", Basic.class, param);

        return output;
    }

    public Address getProfileAddress(String id) {

        Address output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/address/{id}", Address.class, param);

        return output;
    }

    public Contact getProfileContact(String id) {

        Contact output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/contact/{id}", Contact.class, param);

        return output;
    }

    public CurrentOccupation getProfileOccupation(String id) {

        CurrentOccupation output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/currentoccupation/{id}", CurrentOccupation.class, param);

        return output;
    }

    public Education getProfileEducation(String id) {

        Education output;

        Map<String, String> param = new HashMap<>();
        param.put("id", id);

        output = restTemplate.getForObject(uri + "profile/education/{id}", Education.class, param);

        return output;
    }

    public boolean saveProfileBasic(Basic basic) {
        try {
            restTemplate.postForObject(uri + "/profile/basic/", basic, Basic.class);
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }

    public boolean saveProfileAddress(Address address) {
        try {
            restTemplate.postForObject(uri + "/profile/address/", address, Address.class);
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }

    public boolean saveProfileContact(Contact contact) {
        try {
            restTemplate.postForObject(uri + "/profile/contact/", contact, Contact.class);
            return true;
        } catch (RestClientException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean saveProfileOccupation(CurrentOccupation currentOccupation) {
        try {
            restTemplate.postForObject(uri + "/profile/currentoccupation/", currentOccupation, CurrentOccupation.class);
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }

    public boolean saveProfileEducation(Education education) {
        try {
            restTemplate.postForObject(uri + "/profile/education/", education, Education.class);
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }
}
