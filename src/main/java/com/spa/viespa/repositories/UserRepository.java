package com.spa.viespa.repositories;


import com.spa.viespa.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository {
    Optional<User> findByUsername(String username);
}
