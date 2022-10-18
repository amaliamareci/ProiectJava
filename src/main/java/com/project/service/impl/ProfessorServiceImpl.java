package com.project.service.impl;

import com.project.entity.Professor;
import com.project.repository.ProfessorRepository;
import com.project.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        super();
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> getAllProfessor() {
        return professorRepository.findAll();
    }

    public Professor getProfessorByName(String name) {
        return professorRepository.findByName(name);
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor getProfessorById(int id) {
        return professorRepository.findById(id);
    }

    @Override
    public Professor updateProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void deleteProfessorById(int id) {
        professorRepository.deleteById(id);
    }
}
