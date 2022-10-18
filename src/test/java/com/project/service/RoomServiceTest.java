package com.project.service;

import com.project.entity.Course;
import com.project.entity.Room;
import com.project.repository.RoomRepository;
import com.project.service.impl.RoomServiceImpl;
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
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room room;

    @BeforeEach
    public void setUp(){
       room=new Room(1,"RoomTest",34);
    }

    @Test
    void getAllCoursesTest(){
        Room room2=new Room(2,"RoomTest2",100);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room2);
        given(roomRepository.findAll()).willReturn(rooms);
        List<Room> roomList = roomService.getAllRooms();
        assertThat(roomList).isNotNull();
        assertThat(roomList.size()).isEqualTo(2);
    }

    @Test
    void saveRoomTest(){
        given(roomRepository.save(room)).willReturn(room);

        Room savedRoom =roomService.saveRoom(room);

        assertThat(savedRoom).isNotNull();
        assertThat(savedRoom).isEqualTo(room);
    }
    @Test
    void getRoomByIdTest(){
        given(roomRepository.findById(1)).willReturn(room);

        Room savedRoom = roomService.getRoomById(room.getId());

        assertThat(savedRoom).isNotNull();
    }

    @Test
    public void updateRoomTest(){
        given(roomRepository.save(room)).willReturn(room);
        room.setName("AnotherRoom");

        Room updatedRoom = roomService.updateRoom(room);
        assertThat(updatedRoom.getName()).isEqualTo("AnotherRoom");
    }

    @Test
    public void deleteRoomTest(){
        int id=1;
        willDoNothing().given(roomRepository).deleteById(id);
        roomService.deleteRoomById(1);

        verify(roomRepository,times(1)).deleteById(id);
    }

}
