package com.spa.viespa.services;

import com.spa.viespa.entities.ResponseMessage;
import com.spa.viespa.entities.ResponseObject;
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

    //Get All Data in Staff Table
    public ResponseEntity<ResponseObject> getStaffs() {
        List<Staff> all = staffRepository.findAll();
        return all.isEmpty() ? new ResponseObject().response(ResponseMessage.ERROR, "Data not found")
                :new ResponseObject().response("Data of staff table", all);
    }

    //Add New Staff
    public ResponseEntity<ResponseObject> addNewStaff(Staff staff) {

        //Validate
        if(staff.getName() == null) return new ResponseObject()
                .response(ResponseMessage.ERROR, "INVALID DATA");

        //Check duplicated ID in table Staff
        Optional<Staff> duplicatedId = staffRepository.findStaffByIdNo(staff.getIdNo());
        if (duplicatedId.isPresent()) return new ResponseObject()
                .response(ResponseMessage.ERROR,"ID: [" + staff.getIdNo() + "] number existed!");

        //Check duplicated Email in table Staff
        Optional<Staff> duplicatedEmail = staffRepository.findStaffByEmail(staff.getEmail());
        if (duplicatedEmail.isPresent()) return new ResponseObject()
                .response(ResponseMessage.ERROR, "This staff email is already existed");

        //Save
        staffRepository.save(staff);
        return new ResponseObject().response("Insert data successfully", staff);
    }

    //Delete Staff By ID
    public ResponseEntity<ResponseObject> deleteStaff(Long id) {
        boolean exists = staffRepository.existsById(id);
        if (exists) {
            //Delete
            staffRepository.deleteById(id);
            return new ResponseObject().response("Delete data successfully", "");
        }

        return  new ResponseObject().response( ResponseMessage.ERROR,"Staff with ID: [" + id + "] does not exist");
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
        if(!staff.isPresent()) return new ResponseObject()
                .response(ResponseMessage.ERROR,"Staff with ID: [" + id + "] does not exist"));

        Optional<Staff> duplicatedEmail = staffRepository.findStaffByEmail(email);

        if (duplicatedEmail.isPresent()) return new ResponseObject()
                .response(ResponseMessage.ERROR,"This staff email is already existed");

        Staff theStaff = staff.get();

        if (name != null && name.length() > 0 && !Objects.equals(theStaff.getName(), name)) theStaff.setName(name);

        if (dob != null && !Objects.equals(theStaff.getDob(), dob)) theStaff.setDob(dob);

        if (address != null && address.length() > 0 && !Objects.equals(theStaff.getAddress(), address))
            theStaff.setAddress(address);

        if (phone != null && phone.length() > 0 && !Objects.equals(theStaff.getPhone(), phone))
            theStaff.setAddress(phone);

        if (email != null && email.length() > 0 && !Objects.equals(theStaff.getEmail(), email))
            theStaff.setEmail(email);

        if (joinDate != null && !Objects.equals(theStaff.getJoinDate(), joinDate)) theStaff.setJoinDate(joinDate);

        if (!Objects.equals(theStaff.getEndDate(), endDate)) theStaff.setEndDate(endDate);

        return  new ResponseObject().response("Update data successfully", "");
    }
}
