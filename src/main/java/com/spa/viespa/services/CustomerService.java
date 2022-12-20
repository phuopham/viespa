package com.spa.viespa.services;

import com.spa.viespa.entities.Customer;
import com.spa.viespa.entities.ResponseMessage;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Get All Data in Customer Table
    public ResponseEntity<ResponseObject> getCustomers() {
        List<Customer> all = customerRepository.findAll();
        return all.isEmpty() ? ResponseObject.response(ResponseMessage.ERROR, "Data not found")
                : ResponseObject.response("Data of customer table", all);
    }

    //Add New Customer
    public ResponseEntity<ResponseObject> addNewCustomer(Customer customer) {

        //Validate
        if(customer.getName() == null) return ResponseObject
                .response(ResponseMessage.ERROR, "INVALID DATA");

        //Check duplicated phone number in table Customer
        Optional<Customer> duplicatedPhone = customerRepository.findCustomerByPhone(customer.getPhone());
        if (duplicatedPhone.isPresent()) return ResponseObject
                .response(ResponseMessage.ERROR,"Phone: [" + customer.getPhone() + "] already existed!");

        //Check duplicated Email in table Customer
        Optional<Customer> duplicatedEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if (duplicatedEmail.isPresent()) return ResponseObject
                .response(ResponseMessage.ERROR, "This customer email is already existed");

        //Save
        customerRepository.save(customer);
        return ResponseObject.response("Insert data successfully", customer);
    }

    //Delete Customer By ID
    public ResponseEntity<ResponseObject> deleteCustomer(Long id) {
        boolean exists = customerRepository.existsById(id);
        if (exists) {
            //Delete
            customerRepository.deleteById(id);
            return ResponseObject.response("Delete data successfully", "");
        }

        return  ResponseObject.response( ResponseMessage.ERROR,"Customer with ID: [" + id + "] does not exist");
    }

    //Update Customer By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateCustomer(
            Long id, String name, String address, String phone, String email, boolean isFemale) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()) return ResponseObject
                .response(String.valueOf(ResponseMessage.ERROR),"Customer with ID: [" + id + "] does not exist");

        Optional<Customer> duplicatedEmail = customerRepository.findCustomerByEmail(email);

        if (duplicatedEmail.isPresent()) return ResponseObject
                .response(ResponseMessage.ERROR,"This customer email is already existed");

        Customer target = customer.get();

        if (name != null && name.length() > 0 && !Objects.equals(target.getName(), name)) target.setName(name);

        if (phone != null && phone.length() > 0 && !Objects.equals(target.getPhone(), phone))
            target.setAddress(phone);

        if (email != null && email.length() > 0 && !Objects.equals(target.getEmail(), email))
            target.setEmail(email);

        if (address != null && address.length() > 0 && !Objects.equals(target.getAddress(), address))
            target.setAddress(address);
        if(!Objects.equals(target.getIsFemale(), isFemale))target.setIsFemale(!target.getIsFemale());


        return  ResponseObject.response("Update data successfully", "");
    }
}
