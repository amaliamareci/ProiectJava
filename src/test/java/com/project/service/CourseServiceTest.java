package com.project.service;

import com.project.entity.Course;
import com.project.repository.CourseRepository;
import com.project.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;

    @BeforeEach
    public void setup(){
        course=new Course(1,"CourseTest",30);
    }
    @Test
    void saveCourseTest(){
        given(courseRepository.save(course)).willReturn(course);

        Course savedCourse = courseService.saveCourse(course);
        System.out.println(savedCourse);
        assertThat(savedCourse).isNotNull();
    }
    @Test
    void getAllCoursesTest(){
        Course course2=new Course(2,"CourseTest2",100);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course2);
        given(courseRepository.findAll()).willReturn(courses);
        List<Course> courseList = courseService.getAllCourses();
        assertThat(courseList).isNotNull();
        assertThat(courseList.size()).isEqualTo(2);
    }

    @Test
    void getCourseByIdTest(){
        given(courseRepository.findById(1)).willReturn(course);

        Course savedCourse = courseService.getCourseById(course.getId());

        assertThat(savedCourse).isNotNull();
    }
    @Test
    void getCourseByTitleTest(){
        given(courseRepository.findByTitle("CourseTest")).willReturn(course);

        Course savedCourse = courseService.getCourseByTitle(course.getTitle());

        assertThat(savedCourse).isNotNull();
    }

    @Test
    public void updateCourseTest(){
        given(courseRepository.save(course)).willReturn(course);
        course.setTitle("AnotherCourse");

        Course updatedCourse = courseService.updateCourse(course);
        assertThat(updatedCourse.getTitle()).isEqualTo("AnotherCourse");
    }

    @Test
    public void deleteCourseTest(){
        int id=1;
        willDoNothing().given(courseRepository).deleteById(id);
        courseService.deleteCourseById(1);

        verify(courseRepository,times(1)).deleteById(id);
    }
}
