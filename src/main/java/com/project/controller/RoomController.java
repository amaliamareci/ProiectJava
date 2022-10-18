package com.project.controller;

import com.project.entity.Room;
import com.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        super();
        this.roomService = roomService;
    }

    @GetMapping("/all/rooms")
    public ResponseEntity<List<Room>> getAll() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("/rooms")
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }

    @GetMapping("/rooms/new")
    public String createRoomsForm(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        return "create_room";
    }

    @PostMapping("/rooms")
    public String saveRoom(@ModelAttribute("room") Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/rooms/{id}")
    public String deleteRoom(@PathVariable int id) {
        roomService.deleteRoomById(id);
        return "redirect:/rooms";
    }
}
