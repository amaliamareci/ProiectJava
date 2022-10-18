package com.project.controller;

import com.project.entity.Course;
import com.project.entity.Professor;
import com.project.service.CourseService;
import com.project.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessorService professorService;

    public CourseController(CourseService courseService) {
        super();
        this.courseService = courseService;
    }

    @GetMapping("/all/courses")
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/courses/new")
    public String createCourseForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "create_course";
    }

    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/addProfessor/new")
    public String addProfessorForm(Model model) {
        Course course = new Course();
        Professor professor = new Professor();
        model.addAttribute("course", course);
        model.addAttribute("professor", professor);
        return "add_professor";
    }

    @PostMapping("/courses/addProfessor")
    public String saveCourse(@ModelAttribute("course") Course course, @ModelAttribute("professor") Professor professor, Model model) {
        Course existingCourse = courseService.getCourseByTitle(course.getTitle());
        Professor existingProfessor = professorService.getProfessorByName(professor.getName());
        existingCourse.addProfessor(existingProfessor);
        courseService.updateCourse(existingCourse);
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable int id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "edit_course";
    }

    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute("course") Course course, Model model) {

        Course existingCourse = courseService.getCourseById(id);
        existingCourse.setId(id);
        existingCourse.setTitle(course.getTitle());
        existingCourse.setMaxNumberOfStudents(course.getMaxNumberOfStudents());

        courseService.updateCourse(existingCourse);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

}
