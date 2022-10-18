package com.project.controller;

import com.project.entity.Professor;
import com.project.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        super();
        this.professorService = professorService;
    }

    @GetMapping("/all/professors")
    public ResponseEntity<List<Professor>> getAll() {
        return new ResponseEntity<>(professorService.getAllProfessor(), HttpStatus.OK);
    }

    @GetMapping("/professors")
    public String listProfessors(Model model) {
        model.addAttribute("professors", professorService.getAllProfessor());
        return "professors";
    }

    @GetMapping("/professors/new")
    public String createProfessorForm(Model model) {
        Professor professor = new Professor();
        model.addAttribute("professor", professor);
        return "create_professor";
    }

    @PostMapping("/professors")
    public String saveProfessor(@ModelAttribute("professor") Professor professor) {
        professorService.saveProfessor(professor);
        return "redirect:/professors";
    }

    @GetMapping("/professors/edit/{id}")
    public String editProfessorForm(@PathVariable int id, Model model) {
        model.addAttribute("professor", professorService.getProfessorById(id));
        return "edit_professor";
    }

    @GetMapping("/professors/{id}")
    public String deleteProfessor(@PathVariable int id) {
        professorService.deleteProfessorById(id);
        return "redirect:/professor";
    }
}
