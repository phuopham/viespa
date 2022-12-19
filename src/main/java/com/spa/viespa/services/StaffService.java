package com.spa.viespa.services;

import com.spa.viespa.entities.Staff;
import com.spa.viespa.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getStaffs(){
        return staffRepository.findAll();
    }

    public ResponseEntity<ResponseObject> addNewStaff(Staff staff) {
        //Check duplicated ID in table Staff
        Optional<Staff> duplicatedId = staffRepository.findStaffByIdNo(staff.getIdNo());

        //Check duplicated Email in table Staff
        Optional<Staff> duplicatedEmail = staffRepository.findStaffByEmail(staff.getEmail());

        if(duplicatedId.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "ID: ["+ staff.getIdNo() +"] number existed!", "")
            );
        }

        if(duplicatedEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "This staff email is already existed", "")
            );
        }

        staffRepository.save(staff);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Insert data successfully", staff)
        );
    }

    public void deleteStaff(Long id) {
        boolean exists = staffRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Staff with ID: ["+ id +"] does not exist"
            );
        }
        staffRepository.deleteById(id);
    }
    @Transactional
    public void updateStaff(Long id,
                            String name,
                            LocalDate dob,
                            String address,
                            String phone,
                            String email,
                            LocalDate joinDate,
                            LocalDate endDate) {
        Staff staff = staffRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Staff with ID: ["+ id +"] does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(staff.getName(), name)) {
            staff.setName(name);
        }

        if(dob != null && !Objects.equals(staff.getDob(), dob)) {
            staff.setDob(dob);
        }

        if(address != null && address.length() > 0 && !Objects.equals(staff.getAddress(), address)) {
            staff.setAddress(address);
        }

        if(phone != null && phone.length() > 0 && !Objects.equals(staff.getPhone(), phone)) {
            staff.setAddress(phone);
        }

        if(email != null && email.length() > 0 && !Objects.equals(staff.getEmail(), email)) {
            Optional<Staff> duplicatedEmail = staffRepository.findStaffByEmail(staff.getEmail());

            if(duplicatedEmail.isPresent()) {
                throw new IllegalStateException("This staff email is already existed");
            }
            staff.setEmail(email);
        }

        if(joinDate != null && !Objects.equals(staff.getJoinDate(), joinDate)) {
            staff.setJoinDate(joinDate);
        }

        if(!Objects.equals(staff.getEndDate(), endDate)) {
            staff.setEndDate(endDate);
        }
    }
}
