/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.services;

import com.spa.viespa.entities.Course;
import com.spa.viespa.entities.ResponseMessage;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    //Error Message
    public ResponseEntity<ResponseObject> responseSuccess(String msg, Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(msg, data)
        );
    }

    //Success Message
    public ResponseEntity<ResponseObject> responseError(String msg) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(ResponseMessage.ERROR, msg)
        );
    }

    public ResponseEntity<ResponseObject> getCourses() {
        List<Course> all = courseRepository.findAll();
        return all.isEmpty() ?
                responseError("Data not found") :
                responseSuccess("Data of course table", all);
    }

    public ResponseEntity<ResponseObject> addNewCourse(Course course) {
        Optional<Course> duplicatedName = courseRepository.findCourseByName(course.getName());
        if(duplicatedName.isPresent()) {
            responseError("This course name is already existed");
        }

        //Save
        courseRepository.save(course);
        return responseSuccess("Insert data successfully", course);
    }

    public ResponseEntity<ResponseObject> deleteCourse(Long id) {
        boolean exists = courseRepository.existsById(id);

        if(exists) {
            Course course = courseRepository
                    .findById(id)
                    .orElseThrow(() -> new IllegalStateException("Course with ID: ["+ id +"] does not exist"));
            course.setActive(!course.isActive());
            courseRepository.save(course);
            return responseSuccess("Delete data successfully", "");
        }

        return responseError("Course with ID: [" + id + "] does not exist");
    }

    @Transactional
    public ResponseEntity<ResponseObject> updateCourse(Long id,
                                                       String name,
                                                       String description,
                                                       Double price) {
        Course course = courseRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Course with ID: ["+ id +"] does not exist"));

        Optional<Course> duplicatedName = courseRepository.findCourseByName(name);

        if(duplicatedName.isPresent()) {
            return responseError("This course name is already existed");
        }

        if(name != null && name.length() > 0 && !Objects.equals(course.getName(), name)) {
            course.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(course.getDescription(), description)) {
            course.setDescription(description);
        }

        if(price != null && price > 0 && !Objects.equals(course.getPrice(), price)) {
            course.setPrice(price);
        }

        return responseSuccess("Update data successfully", "");
    }
}
