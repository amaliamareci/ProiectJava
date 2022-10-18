package com.project.service.impl;

import com.project.entity.Course;
import com.project.repository.CourseRepository;
import com.project.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        super();
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course getCourseByTitle(String title) {
        return courseRepository.findByTitle(title);
    }
}
