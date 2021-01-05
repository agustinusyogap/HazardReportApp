/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.hazardreportapp.services;

import com.ksm.hazardreportapp.entities.ImageAttachments;
import com.ksm.hazardreportapp.repositories.ImageAttachmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author YOGA
 */
@Service
public class ImageAttachmentService {

    @Autowired
    ImageAttachmentRepository repository;

    public List<ImageAttachments> getAll() {
        return repository.findAll();
    }

    public ImageAttachments getById(Integer id) {
        return repository.findById(id).get();
    }

    public boolean save(ImageAttachments image) {
        try {
            repository.save(image);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(ImageAttachments image) {
        try {
            repository.save(image);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
