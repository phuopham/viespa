package com.spa.viespa.repositories;

import com.spa.viespa.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {


    Optional<Staff> findStaffById(Long aLong);

    @Query("SELECT s FROM Staff s WHERE s.idNo =?1")
    Optional<Staff> findStaffByIdNo(Long searchTerm);
}
