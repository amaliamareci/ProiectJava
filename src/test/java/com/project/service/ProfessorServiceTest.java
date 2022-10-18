package com.project.service;

import com.project.entity.Professor;
import com.project.entity.Room;
import com.project.repository.ProfessorRepository;
import com.project.service.impl.ProfessorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {
    @Mock
    ProfessorRepository professorRepository;
    @InjectMocks
    ProfessorServiceImpl professorService;

    Professor professor;
    @BeforeEach
    public void setUp(){
        professor=new Professor(1,"ProfessorTest");
    }
    @Test
    void saveProfessorTest(){
        given(professorRepository.save(professor)).willReturn(professor);

        Professor savedProfessor =professorService.saveProfessor(professor);

        assertThat(savedProfessor).isNotNull();
        assertThat(savedProfessor).isEqualTo(professor);
    }
    @Test
    void getAllProfessorsTest(){
        Professor professor2 =new Professor(2,"TestProfessor2");
        ArrayList<Professor> professors = new ArrayList<>();
        professors.add(professor);
        professors.add(professor2);
        given(professorRepository.findAll()).willReturn(professors);
        List<Professor> roomList = professorService.getAllProfessor();
        assertThat(roomList).isNotNull();
        assertThat(roomList.size()).isEqualTo(2);
    }


    @Test
    void getProfessorByIdTest(){
        given(professorRepository.findById(1)).willReturn(professor);

        Professor savedProfessor= professorService.getProfessorById(professor.getId());

        assertThat(savedProfessor).isNotNull();
    }

    @Test
    public void updateProfessorTest(){
        given(professorRepository.save(professor)).willReturn(professor);
        professor.setName("AnotherProfessor");

        Professor updatedProfessor= professorService.updateProfessor(professor);
        assertThat(updatedProfessor.getName()).isEqualTo("AnotherProfessor");
    }

    @Test
    public void deleteProfessorTest(){
        int id=1;
        willDoNothing().given(professorRepository).deleteById(id);
        professorService.deleteProfessorById(1);

        verify(professorRepository,times(1)).deleteById(id);
    }
}
