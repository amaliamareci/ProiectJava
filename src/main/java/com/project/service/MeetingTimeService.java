package com.project.service;

import com.project.entity.MeetingTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingTimeService {
    List<MeetingTime> getAllMeetingTime();

    MeetingTime saveMeetingTime(MeetingTime meetingTime);

    MeetingTime getMeetingTimeById(int id);

    MeetingTime updateMeetingTime(MeetingTime meetingTime);

    void deleteMeetingTimeById(int id);
}
