/**
 * Author: ng-hoangnam
 * Date: 20/12/2022
 * Description:
 */

package com.spa.viespa.controllers;

import com.spa.viespa.entities.Course;
import com.spa.viespa.entities.ResponseObject;
import com.spa.viespa.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addNewCourse(@RequestBody Course course) {
        return courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseId}")
    public ResponseEntity<ResponseObject> deleteCourse(@PathVariable("courseId") Long id) {
        return courseService.deleteCourse(id);
    }

    @PutMapping(path = "{courseId}")
    public ResponseEntity<ResponseObject> updateCourse(
            @PathVariable("courseId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price) {
        return courseService.updateCourse(id, name, description, price);
    }
}
