package com.spa.viespa.repositories;

import com.spa.viespa.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT it FROM Customer it WHERE it.id =?1")
    Optional<Customer> findCustomerById(Long id);

    @Query("SELECT it FROM Customer it WHERE it.email = ?1")
    Optional<Customer> findCustomerByEmail(String email);

    @Query("SELECT it FROM Customer it WHERE it.phone = ?1")
    Optional<Customer> findCustomerByPhone(String phone);
}
