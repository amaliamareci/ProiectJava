package com.project.service;

import com.project.entity.Professor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessorService {
    List<Professor> getAllProfessor();

    Professor saveProfessor(Professor professor);

    Professor getProfessorById(int id);

    Professor updateProfessor(Professor professor);

    void deleteProfessorById(int id);

    Professor getProfessorByName(String name);
}
