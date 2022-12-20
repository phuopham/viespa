package com.spa.viespa.controllers;

import com.spa.viespa.entities.Customer;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addNewCustomer(@RequestBody Customer customer) {
        return customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable("customerId") Long id) {
        return customerService.deleteCustomer(id);
    }

    @PutMapping(path = "{customerId}")
    public ResponseEntity<ResponseObject> updateCustomer(
            @PathVariable("customerId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam Boolean isFemale
    ) {
        return customerService.updateCustomer(id, name, address, phone, email, isFemale);
    }
}
