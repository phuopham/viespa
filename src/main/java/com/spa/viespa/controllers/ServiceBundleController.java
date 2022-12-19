/**
 * Author: Aptech - Team 1 - Project Semester 2
 * Date: 18/12/2022
 * Description: VieSpa Management Project
 */

package com.spa.viespa.controllers;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.ServiceBundle;
import com.spa.viespa.services.ServiceBundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/services")
public class ServiceBundleController {
    private final ServiceBundleService service;

    @Autowired
    public ServiceBundleController(ServiceBundleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getService(){
        return service.getServiceBundles();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addNewService(@RequestBody ServiceBundle serviceBundle) {
        return service.addNewSkill(serviceBundle);
    }

    @DeleteMapping(path = "{serviceId}")
    public ResponseEntity<ResponseObject> deleteService(@PathVariable("serviceId") Long id) {
        return service.deleteService(id);
    }

    @PutMapping(path = "{serviceId}")
    public ResponseEntity<ResponseObject> updateService(
            @PathVariable("serviceId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        return service.updateServiceBundle(id, name, description);
    }

}
