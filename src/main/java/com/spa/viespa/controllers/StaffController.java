package com.spa.viespa.controllers;

import com.spa.viespa.entities.Staff;
import com.spa.viespa.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public void addNewStaff(@RequestBody Staff staff) {
        staffService.addNewStaff(staff);
    }

    @DeleteMapping(path = "{staffId}")
    public void deleteStaff(@PathVariable("staffId") Long id) {
        staffService.deleteStaff(id);
    }

    @PutMapping(path = "{staffId}")
    public void updateStaff(
            @PathVariable("staffId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate dob,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate joinDate,
            @RequestParam(required = false) LocalDate endDate) {
        staffService.updateStaff(id, name, dob, address, phone, email, joinDate, endDate);
    }
}
