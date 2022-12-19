/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.ResponseMessage;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.ServiceBundle;
import com.spa.viespa.repositories.ServiceBundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceBundleService {

    private final ServiceBundleRepository serviceBundleRepository;

    @Autowired
    public ServiceBundleService(ServiceBundleRepository serviceBundleRepository) {
        this.serviceBundleRepository = serviceBundleRepository;
    }

    //Error Message
    public ResponseEntity<ResponseObject> responseSuccess(String msg, Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(msg, data)
        );
    }

    //Success Message
    public ResponseEntity<ResponseObject> responseError(String msg) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(ResponseMessage.ERROR, msg)
        );
    }

    //Get All Data in Service Table
    public ResponseEntity<ResponseObject> getServiceBundles() {
        List<ServiceBundle> all = serviceBundleRepository.findAll();
        return all.isEmpty() ?
                responseError("Data not found") :
                responseSuccess("Data of service table", all);
    }

    //Add New Service
    public ResponseEntity<ResponseObject> addNewSkill(ServiceBundle serviceBundle) {
        Optional<ServiceBundle> duplicatedName = serviceBundleRepository.findServiceByName(serviceBundle.getName());
        if(duplicatedName.isPresent()) {
            responseError("This service name is already existed");
        }

        //Save
        serviceBundleRepository.save(serviceBundle);
        return responseSuccess("Insert data successfully", serviceBundle);
    }

    //Delete Service By ID
    public ResponseEntity<ResponseObject> deleteService(Long id) {
        boolean exists = serviceBundleRepository.existsById(id);

        if(exists) {
            ServiceBundle serviceBundle = serviceBundleRepository
                    .findServiceById(id)
                    .orElseThrow(() -> new IllegalStateException("Service with ID: ["+ id +"] does not exist"));
            serviceBundle.setActive(!serviceBundle.isActive());
            serviceBundleRepository.save(serviceBundle);
            return responseSuccess("Delete data successfully", "");
        }

        return responseError("Service with ID: [" + id + "] does not exist");
    }


    //Update Skill By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateServiceBundle(Long id,
                            String name,
                            String description) {
        ServiceBundle service = serviceBundleRepository
                .findServiceById(id)
                .orElseThrow(() -> new IllegalStateException("Service with ID: ["+ id +"] does not exist"));

        Optional<ServiceBundle> duplicatedName = serviceBundleRepository.findServiceByName(name);

        if(duplicatedName.isPresent()) {
            return responseError("This service name is already existed");
        }

        if(name != null && name.length() > 0 && !Objects.equals(service.getName(), name)) {
            service.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(service.getDescription(), description)) {
            service.setDescription(description);
        }

        return responseSuccess("Update data successfully", "");
    }
}
