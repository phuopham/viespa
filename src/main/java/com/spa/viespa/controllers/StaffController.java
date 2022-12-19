package com.spa.viespa.controllers;

import com.spa.viespa.entities.Staff;
import com.spa.viespa.services.ResponseObject;
import com.spa.viespa.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping(path="api/v1/staffs")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getStaffs(){
        return staffService.getStaffs();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addNewStaff(@RequestBody Staff staff) {
        return staffService.addNewStaff(staff);
    }

    @DeleteMapping(path = "{staffId}")
    public ResponseEntity<ResponseObject> deleteStaff(@PathVariable("staffId") Long id) {
        return staffService.deleteStaff(id);
    }

    @PutMapping(path = "{staffId}")
    public ResponseEntity<ResponseObject> updateStaff(
            @PathVariable("staffId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate dob,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate joinDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        return staffService.updateStaff(id, name, dob, address, phone, email, joinDate, endDate);
    }
}
