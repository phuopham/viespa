/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.Course;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //Get All Course Table
    public ResponseEntity<ResponseObject> getCourses() {
        List<Course> all = courseRepository.findAll();
        return all.isEmpty() ?
                ResponseObject.response("Data not found") :
                ResponseObject.response("Data of course table", all);
    }

    //Get Detail Course By ID
    public ResponseEntity<ResponseObject> getDetailCourse(Long id) {

        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) return ResponseObject
                .response("Course with ID: [" + id + "] does not exist");

        Course target = course.get();

        return ResponseObject.response("Get course [" + id + "] successfully", target);
    }

    //Add New Course
    public ResponseEntity<ResponseObject> addNewCourse(Course course) {

        Optional<Course> duplicatedName = courseRepository.findCourseByName(course.getName());
        if(duplicatedName.isPresent()) {
            ResponseObject.response("This course name is already existed");
        }

        //Save
        courseRepository.save(course);
        return ResponseObject.response("Insert data successfully", course);
    }

    //Delete Course By ID
    public ResponseEntity<ResponseObject> deleteCourse(Long id) {

        Optional<Course> course = courseRepository.findById(id);

        if (course.isEmpty()) return ResponseObject
                .response("Course with ID: [" + id + "] does not exist");

        //Soft Delete
        Course target = course.get();
        target.setActive(!target.isActive());
        courseRepository.save(target);

        return ResponseObject.response("Delete data successfully", target);
    }

    //Update Course By ID
    @Transactional
    public ResponseEntity<ResponseObject> updateCourse(Long id,
                                                       String name,
                                                       String description,
                                                       Double price) {

        Optional<Course> course = courseRepository.findById(id);

        if (course.isEmpty()) return ResponseObject
                .response("Service with ID: [" + id + "] does not exist");

        Optional<Course> duplicatedName = courseRepository.findCourseByName(name);

        if(duplicatedName.isPresent()) {
            return ResponseObject.response("This course name is already existed");
        }

        Course target = course.get();

        if(name != null && name.length() > 0 && !Objects.equals(target.getName(), name)) {
            target.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(target.getDescription(), description)) {
            target.setDescription(description);
        }

        if(price != null && price > 0 && !Objects.equals(target.getPrice(), price)) {
            target.setPrice(price);
        }

        return ResponseObject.response("Update data successfully", "");
    }
}
