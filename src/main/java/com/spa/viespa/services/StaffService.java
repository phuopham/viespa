package com.spa.viespa.services;

import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.entities.Staff;
import com.spa.viespa.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Get All Data in Staff Table
    public ResponseEntity<ResponseObject> getStaffs() {
        List<Staff> all = staffRepository.findAll();
        return all.isEmpty() ?
                ResponseObject.response("Data not found") :
                ResponseObject.response("Data of staff table", all);
    }

    //Add New Staff
    public ResponseEntity<ResponseObject> addNewStaff(Staff staff) {

        //Validate
        if (staff.getName() == null) return ResponseObject
                .response("INVALID DATA");

        //Check duplicated ID in table Staff
        Optional<Staff> duplicatedId = staffRepository.findStaffByIdNo(staff.getIdNo());
        if (duplicatedId.isPresent()) return ResponseObject
                .response("ID: [" + staff.getIdNo() + "] number existed!");

        //Check duplicated Email in table Staff
        Optional<Staff> duplicatedEmail = staffRepository.findStaffByEmail(staff.getEmail());
        if (duplicatedEmail.isPresent()) return ResponseObject
                .response("This staff email is already existed");

        //Save
        staffRepository.save(staff);
        return ResponseObject.response("Insert data successfully", staff);
    }

    //Delete Staff By ID
    public ResponseEntity<ResponseObject> deleteStaff(Long id) {
        boolean exists = staffRepository.existsById(id);

        if (exists) {
            //Delete
            staffRepository.deleteById(id);
            return ResponseObject.response("Delete data successfully", "");
        }

        return ResponseObject.response("Staff with ID: [" + id + "] does not exist");
    }

    //Update Staff By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateStaff(Long id,
                                                      String name,
                                                      LocalDate dob,
                                                      String address,
                                                      String phone,
                                                      String email,
                                                      LocalDate joinDate,
                                                      LocalDate endDate) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isEmpty()) return ResponseObject
                .response("Staff with ID: [" + id + "] does not exist");

        Optional<Staff> duplicatedEmail = staffRepository.findStaffByEmail(email);

        if (duplicatedEmail.isPresent()) return ResponseObject
                .response("This staff email is already existed");

        Staff target = staff.get();

        if (name != null && name.length() > 0 && !Objects.equals(target.getName(), name))
            target.setName(name);

        if (dob != null && !Objects.equals(target.getDob(), dob))
            target.setDob(dob);

        if (address != null && address.length() > 0 && !Objects.equals(target.getAddress(), address))
            target.setAddress(address);

        if (phone != null && phone.length() > 0 && !Objects.equals(target.getPhone(), phone))
            target.setAddress(phone);

        if (email != null && email.length() > 0 && !Objects.equals(target.getEmail(), email))
            target.setEmail(email);

        if (joinDate != null && !Objects.equals(target.getJoinDate(), joinDate))
            target.setJoinDate(joinDate);

        if (!Objects.equals(target.getEndDate(), endDate))
            target.setEndDate(endDate);

        return ResponseObject.response("Update data successfully", target);
    }
}
