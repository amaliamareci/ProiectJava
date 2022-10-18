package com.project.service;

import com.project.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<Room> getAllRooms();

    Room saveRoom(Room room);

    Room getRoomById(int id);

    Room updateRoom(Room room);

    void deleteRoomById(int id);
}
