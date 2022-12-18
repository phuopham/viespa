package com.spa.viespa.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void addNewStaff(Staff staff) {
        Optional<Staff> staffOptional = staffRepository.findStaffByIdNo(staff.getIdNo());
        if(staffOptional.isPresent()){
            throw new IllegalStateException("ID number existed!");
        }
        System.out.println(staff);
        staffRepository.save(staff);
    }

    public void deleteStaff(Long staffId) {
        boolean exists = staffRepository.existsById(staffId);
        if(!exists){
            throw new IllegalStateException(
                    "Staff with id " + staffId +" does not exist"
            );
        }
        staffRepository.deleteById(staffId);
    }
}
