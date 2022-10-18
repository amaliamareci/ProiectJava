package com.project.controllers;

import com.project.controller.RoomController;
import com.project.entity.Room;
import com.project.service.RoomService;
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

@WebMvcTest(RoomController.class)
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomService roomService;

    private List<Room> rooms = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.rooms.add(new Room(1, "Room1", 10));
        this.rooms.add(new Room(2, "Room2", 20));

    }

    @Test
    void testAllRooms() throws Exception {
        String expected = "[" + (new Room(1, "Room1", 10).toJson()) + ",";
        expected = expected + (new Room(2, "Room2", 20)).toJson();
        expected = expected + "]";
        //System.out.println(expected);
        given(roomService.getAllRooms()).willReturn(rooms);

        MockHttpServletResponse response = this.mockMvc.perform(get("/all/rooms").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        // System.out.println(response.getContentAsString());
        assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void testListRooms() throws Exception {
        given(roomService.getAllRooms()).willReturn(rooms);

        MockHttpServletResponse response = this.mockMvc.perform(get("/rooms").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testCreateRoomForm() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/rooms/new").accept(MediaType.ALL)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testSaveRoom() throws Exception {
        given(roomService.saveRoom(rooms.get(0))).willReturn(rooms.get(0));

        MockHttpServletResponse response = this.mockMvc.perform(post("/rooms").accept(MediaType.ALL)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        System.out.println(response.getRedirectedUrl());
        assertThat(response.getRedirectedUrl()).isEqualTo("/rooms");
    }

    /*    @Test
        void testEditRoomForm()throws Exception{
            given(roomService.getRoomById(1)).willReturn(rooms.get(1));
            MockHttpServletResponse response=this.mockMvc.perform(get("/rooms/edit/1").accept(MediaType.ALL)).andReturn().getResponse();
            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            assertThat(response.getContentAsString()).isEqualTo("edit_room");
        }*/
    @Test
    void testDeleteRoom() throws Exception {
        doNothing().when(roomService).deleteRoomById(1);
        MockHttpServletResponse response = this.mockMvc.perform(get("/rooms/1").accept(MediaType.ALL)).andReturn().getResponse();
        System.out.println();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
        assertThat(response.getRedirectedUrl()).isEqualTo("/rooms");
    }
}
