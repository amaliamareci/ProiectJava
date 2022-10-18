package com.project.service;

import com.project.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> getAllCourses();

    Course saveCourse(Course course);

    Course getCourseById(int id);

    Course updateCourse(Course course);

    void deleteCourseById(int id);

    Course getCourseByTitle(String title);
}
