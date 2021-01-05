/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services.rest;

import com.ksm.hazardreportapp.entities.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author kelvi
 */
@Service
public class LoginService {

    @Autowired
    RestTemplate restTemplate;

    private final String uri = "http://116.254.101.228:8080/ma_test/";

    public LoginOutput login(LoginInput input) {
        HttpEntity<LoginInput> request = new HttpEntity<>(input, null);
        ResponseEntity<LoginOutput> responseEntity = restTemplate.exchange(
                uri + "login",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<LoginOutput>() {
        });

        return responseEntity.getBody();
    }
}
