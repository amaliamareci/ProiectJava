package com.project.controllers;

import com.project.controller.ProfessorController;
import com.project.entity.Professor;
import com.project.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProfessorService professorService;
    private List<Professor> professors = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.professors.add(new Professor(1, "Professor1"));
        this.professors.add(new Professor(2, "Professor2"));

    }

    @Test
    void testAllProfessors() throws Exception {
        String expected = "[" + (new Professor(1, "Professor1").toJson()) + ",";
        expected = expected + (new Professor(2, "Professor2")).toJson();
        expected = expected + "]";
        //System.out.println(expected);
        given(professorService.getAllProfessor()).willReturn(professors);

        MockHttpServletResponse response = this.mockMvc.perform(get("/all/professors").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // System.out.println(response.getContentAsString());
        assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void testListProfessors() throws Exception {
        given(professorService.getAllProfessor()).willReturn(professors);

        MockHttpServletResponse response = this.mockMvc.perform(get("/professors").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testCreateProfessorForm() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/professors/new").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveProfessor() throws Exception {
        given(professorService.saveProfessor(professors.get(0))).willReturn(professors.get(0));

        MockHttpServletResponse response = this.mockMvc.perform(post("/professors").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/professors");
    }

    @Test
    void testDeleteProfessor() throws Exception {
        doNothing().when(professorService).deleteProfessorById(1);
        MockHttpServletResponse response = this.mockMvc.perform(get("/professors/1").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/professor");
    }
}
