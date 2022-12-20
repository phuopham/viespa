package com.spa.viespa.repositories;

import com.spa.viespa.entities.Course;
import com.spa.viespa.entities.Customer;
import com.spa.viespa.entities.SpaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpaTransactionRepository extends JpaRepository<SpaTransaction, Long> {
    @Query("SELECT it FROM Course it WHERE it.id =?1")
    Optional<Course> findCourseById(Long id);

    @Query("SELECT it FROM Customer it WHERE it.id = ?1")
    Optional<Customer> findCustomerById(Long id);
}
