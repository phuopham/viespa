package com.spa.viespa.controllers;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.Staff;
import com.spa.viespa.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping(path="api/v1/staffs")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> getStaffs(){
        return staffService.getStaffs();
    }

    @GetMapping(path = "{staffId}")
    public ResponseEntity<ResponseObject> getDetailStaff(@PathVariable("staffId") Long id) {
        return staffService.getDetailStaff(id);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addNewStaff(@RequestBody Staff<Set<Integer>> staff) {
        return staffService.addNewStaff(staff);
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
