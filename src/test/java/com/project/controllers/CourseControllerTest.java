package com.project.controllers;

import com.project.controller.CourseController;
import com.project.entity.Course;
import com.project.service.CourseService;
import com.project.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private ProfessorService professorService;

    private JacksonTester<List<Course>> jsonCourse;

    private List<Course> courses = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.courses.add(new Course(1, "Curs1", 10));
        this.courses.add(new Course(2, "Curs2", 20));

    }

    @Test
    void testAllCourses() throws Exception {
        String expected = "[" + (new Course(1, "Curs1", 10).toJson()) + ",";
        expected = expected + (new Course(2, "Curs2", 20)).toJson();
        expected = expected + "]";
        //System.out.println(expected);
        given(courseService.getAllCourses()).willReturn(courses);

        MockHttpServletResponse response = this.mockMvc.perform(get("/all/courses").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        System.out.println(response.getContentAsString());
        assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void testListCourses() throws Exception {
        given(courseService.getAllCourses()).willReturn(courses);

        MockHttpServletResponse response = this.mockMvc.perform(get("/courses").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testCreateCourseForm() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/courses/new").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testSaveCourse() throws Exception {
        given(courseService.saveCourse(new Course("Curs1", 10))).willReturn(new Course("Curs1", 10));

        MockHttpServletResponse response = this.mockMvc.perform(post("/courses").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/courses");
    }

    @Test
    void testAddProfessorForm() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/courses/addProfessor/new").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    ////nu merge inca
   /* @Test
    void testSaveCourseWithProfessors() throws Exception{
        given(courseService.getCourseByTitle("Curs1")).willReturn(new Course(1,"Curs1",10));
        given(professorService.getProfessorByName("Professor1")).willReturn(new Professor(1,"Professor1"));

        Set<Professor> professors =new HashSet<>();
        professors.add(new Professor(1,"Professor1") );

        given(courseService.saveCourse(new Course(1,"Curs1",10,professors))).willReturn(new Course(1,"Curs1",10,professors));


        MockHttpServletResponse response=this.mockMvc.perform(post("/courses/addProfessor").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("redirect:/courses");
    }*/


    @Test
    void testUpdateCourse() throws Exception {
        given(courseService.getCourseById(1)).willReturn(new Course(1, "Curs1", 10));
        given(courseService.updateCourse(new Course(1, "Curs1", 10))).willReturn(new Course(1, "Curs1", 10));
        MockHttpServletResponse response = this.mockMvc.perform(post("/courses/1").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/courses");
    }

    @Test
    void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourseById(1);
        MockHttpServletResponse response = this.mockMvc.perform(get("/courses/1").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/courses");
    }
}