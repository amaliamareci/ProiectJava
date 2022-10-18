package com.project.repository;

import com.project.entity.MeetingTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Component
public interface MeetingTimeRepository extends CrudRepository<MeetingTime, Integer> {
    ArrayList<MeetingTime> findAll();

    MeetingTime findById(int id);
}
