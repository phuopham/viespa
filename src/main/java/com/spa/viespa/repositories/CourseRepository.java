package com.spa.viespa.repositories;

import com.spa.viespa.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT it FROM Course it WHERE it.name = ?1 AND it.active = true")
    Optional<Course> findCourseByName(String name);
}
