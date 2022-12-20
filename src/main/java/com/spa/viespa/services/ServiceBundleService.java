/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.ServiceBundle;
import com.spa.viespa.repositories.ServiceBundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Get All Data in Service Table
    public ResponseEntity<ResponseObject> getServiceBundles() {
        List<ServiceBundle> all = serviceBundleRepository.findAll();
        return all.isEmpty() ?
                ResponseObject.response("Data not found") :
                ResponseObject.response("Data of service table", all);
    }

    //Add New Service
    public ResponseEntity<ResponseObject> addNewSkill(ServiceBundle serviceBundle) {
        Optional<ServiceBundle> duplicatedName = serviceBundleRepository.findServiceByName(serviceBundle.getName());
        if (duplicatedName.isPresent()) {
            ResponseObject.response("This service name is already existed");
        }

        //Save
        serviceBundleRepository.save(serviceBundle);
        return ResponseObject.response("Insert data successfully", serviceBundle);
    }

    //Delete Service By ID
    public ResponseEntity<ResponseObject> deleteService(Long id) {

        Optional<ServiceBundle> serviceBundle = serviceBundleRepository.findById(id);
        if (serviceBundle.isEmpty()) return ResponseObject
                .response("Service with ID: [" + id + "] does not exist");

        ServiceBundle target = serviceBundle.get();

        target.setActive(!target.isActive());
        serviceBundleRepository.save(target);

        return ResponseObject.response("Delete data successfully", target);
    }


    //Update Skill By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateServiceBundle(Long id,
                                                              String name,
                                                              String description) {
        Optional<ServiceBundle> service = serviceBundleRepository.findById(id);

        if (service.isEmpty()) return ResponseObject
                .response("Service with ID: [" + id + "] does not exist");

        Optional<ServiceBundle> duplicatedName = serviceBundleRepository.findServiceByName(name);

        if (duplicatedName.isPresent()) return ResponseObject
                .response("This service name is already existed");

        ServiceBundle target = service.get();

        if (name != null && name.length() > 0 && !Objects.equals(target.getName(), name))
            target.setName(name);

        if (description != null && description.length() > 0 && !Objects.equals(target.getDescription(), description))
            target.setDescription(description);


        return ResponseObject.response("Update data successfully", target);
    }
}
