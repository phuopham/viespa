package com.spa.viespa.controllers;

import com.spa.viespa.entities.Staff;
import com.spa.viespa.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/staffs")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @GetMapping
    public List<Staff> getStaffs(){
        return staffService.getStaffs();
    }

    @PostMapping
    public void addNewStaff(@RequestBody Staff staff){ staffService.addNewStaff(staff); }

    @DeleteMapping(path = "{staffId}")
    public void deleteStaff(@PathVariable("staffId") Long id){
        staffService.deleteStaff(id);
    }
}
