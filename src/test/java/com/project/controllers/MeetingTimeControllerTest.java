package com.project.controllers;

import com.project.controller.MeetingTimeController;
import com.project.entity.MeetingTime;
import com.project.service.MeetingTimeService;
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

@WebMvcTest(MeetingTimeController.class)
public class MeetingTimeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeetingTimeService meetingTimeService;

    private List<MeetingTime> meetingTimes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.meetingTimes.add(new MeetingTime(1, "Mon 12:00-14:00"));
        this.meetingTimes.add(new MeetingTime(2, "Mon 14:00-16:00"));
    }

    @Test
    void testAllRooms() throws Exception {
        String expected = "[" + (new MeetingTime(1, "Mon 12:00-14:00").toJson()) + ",";
        expected = expected + (new MeetingTime(2, "Mon 14:00-16:00")).toJson();
        expected = expected + "]";
        //System.out.println(expected);
        given(meetingTimeService.getAllMeetingTime()).willReturn(meetingTimes);

        MockHttpServletResponse response = this.mockMvc.perform(get("/all/meetingTimes").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        System.out.println(response.getContentAsString());
        assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void testListMeetingTimes() throws Exception {
        given(meetingTimeService.getAllMeetingTime()).willReturn(meetingTimes);

        MockHttpServletResponse response = this.mockMvc.perform(get("/meetingTimes").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testCreateMeetingTimeForm() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/meetingTimes/new").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveMeetingTime() throws Exception {
        given(meetingTimeService.saveMeetingTime(meetingTimes.get(0))).willReturn(meetingTimes.get(0));

        MockHttpServletResponse response = this.mockMvc.perform(post("/meetingTimes").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/meetingTimes");
    }

    @Test
    void testDeleteMeetingTime() throws Exception {
        doNothing().when(meetingTimeService).deleteMeetingTimeById(1);
        MockHttpServletResponse response = this.mockMvc.perform(get("/meetingTimes/1").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/meetingTimes");
    }
}
