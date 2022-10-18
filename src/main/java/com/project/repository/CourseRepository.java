package com.project.repository;

import com.project.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Component
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findById(int id);

    ArrayList<Course> findAll();

    Course findByTitle(String title);
}
