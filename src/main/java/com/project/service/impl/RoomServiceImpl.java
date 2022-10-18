package com.project.service.impl;

import com.project.entity.Room;
import com.project.repository.RoomRepository;
import com.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        super();
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        return (List<Room>) roomRepository.findAll();
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(int id) {
        roomRepository.deleteById(id);
    }
}
