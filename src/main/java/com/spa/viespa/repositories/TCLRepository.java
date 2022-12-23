package com.spa.viespa.repositories;

import com.spa.viespa.entities.Course;
import com.spa.viespa.entities.Customer;
import com.spa.viespa.entities.Staff;
import com.spa.viespa.entities.TransactionCourseLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TCLRepository extends JpaRepository<TransactionCourseLine, Long> {

    @Query("SELECT it FROM Customer it WHERE it.id = ?1")
    Optional<Customer> findCustomerById(Long id);

    @Query("SELECT course FROM Course course WHERE course.id =?1 AND course.active = true")
    Optional<Course> findCourseById(Long id);

    @Query("SELECT it FROM ServiceBundle it WHERE it.id =?1 AND it.active = true")
    Optional<Course> findServiceById(Long id);

    @Query("SELECT it FROM Skill it WHERE it.id = ?1 AND it.active = true")
    Optional<Customer> findSkillById(Long id);

    @Query("SELECT it FROM Staff it WHERE it.id = ?1 AND it.endDate IS NULL")
    Optional<Staff> findStaffById(Long id);
}
