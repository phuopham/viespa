package com.spa.viespa.repositories;

import com.spa.viespa.entities.Skill;
import com.spa.viespa.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("SELECT it FROM Staff it WHERE it.idNo =?1")
    Optional<Staff> findStaffByIdNo(Long searchTerm);

    @Query("SELECT it FROM Staff it WHERE it.email = ?1")
    Optional<Staff> findStaffByEmail(String email);

    @Query("SELECT it FROM Skill it WHERE it.id = ?1")
    Optional<Skill> findSkillById(Long id);
}
