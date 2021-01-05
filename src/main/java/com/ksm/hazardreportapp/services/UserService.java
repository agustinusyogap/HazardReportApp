/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.Notifications;
import com.ksm.hazardreportapp.entities.Users;
import com.ksm.hazardreportapp.entities.rest.Majors;
import com.ksm.hazardreportapp.entities.rest.RegisterInput;
import com.ksm.hazardreportapp.entities.rest.RegisterOutput;
import com.ksm.hazardreportapp.entities.rest.Universities;
import com.ksm.hazardreportapp.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Boona
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RoleService roleService;

    private final String uri = "http://116.254.101.228:8080/ma_test/";
    RegisterOutput registerOutput = new RegisterOutput();
    Users newUser = new Users();

    public List<Users> getAll() {
        return repository.findAll();
    }

    public RegisterOutput register(RegisterInput input) {
        try {
            HttpEntity<RegisterInput> request = new HttpEntity<>(input, null);
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                    uri + "register",
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Boolean>() {
            });
            System.out.println(responseEntity);
            registerOutput.setStatus(true);
            registerOutput.setMessage("register_success");

            newUser.setId("TEMP-000" + this.getAll().size() + 1);
            newUser.setEmail(input.getEmail());
            newUser.setName(input.getName());
            newUser.setPhone(input.getPhone());
            newUser.setUsername(input.getUsername());
            newUser.setRoles(roleService.getById(3));

            this.save(newUser);

            return registerOutput;
        } catch (HttpStatusCodeException e) {
            registerOutput.setStatus(false);
            String message = e.getMessage().split(":")[1].replace("[", "").replace("]", "");
            registerOutput.setMessage(message);

            return registerOutput;
        }

    }

    public Users getById(String id) {
        return repository.findById(id).get();
    }

    public List<Users> getByRoles(int id) {
        return repository.findByRoles(roleService.getById(id));
    }

    public Users save(Users users) {
        return repository.save(users);
    }

    public void delete(String id) {
        repository.delete(new Users(id));
    }

    public List<Majors> getMajors() {
        List<Majors> result;

        ResponseEntity<List<Majors>> response = restTemplate.exchange(uri + "get/majors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Majors>>() {
        });

        result = response.getBody();
        return result;
    }

    public List<Universities> getUniversities() {
        List<Universities> result;

        ResponseEntity<List<Universities>> response = restTemplate.exchange(uri + "get/universities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Universities>>() {
        });

        result = response.getBody();
        return result;
    }

    public Users getByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Users getByEmail(String email) {
        try {
            return repository.findByEmail(email);
        } catch (Exception e) {
            System.out.println("GetByEmail error : " + e);
            return null;
        }
    }

    public int syncLocalUserIdAndServerUserId(String id, String email) {
        try {
            return repository.syncLocalUserIdAndServerUserId(id, email);
        } catch (Exception e) {
            System.out.println("Error in updating ID : " + e);
            return 0;
        }
    }

    public boolean updateRole(String username, int role) {
        Users users = new Users();
        try {
            users = repository.findByUsername(username);
            users.setRoles(roleService.getById(role));
            repository.save(users);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Notifications> getNotification(String id) {
        return repository.findById(id).get().getNotificationsList();
    }
}
