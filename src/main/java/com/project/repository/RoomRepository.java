package com.project.repository;

import com.project.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Component
public interface RoomRepository extends CrudRepository<Room, Integer> {
    ArrayList<Room> findAll();

    Room findById(int id);
}
