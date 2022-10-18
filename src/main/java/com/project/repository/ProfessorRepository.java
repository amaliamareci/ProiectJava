package com.project.repository;

import com.project.entity.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Component
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {
    Professor findById(int id);

    ArrayList<Professor> findAll();

    Professor findByName(String name);
}
