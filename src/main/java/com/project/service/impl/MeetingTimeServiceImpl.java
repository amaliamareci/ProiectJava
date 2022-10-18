package com.project.service.impl;

import com.project.entity.MeetingTime;
import com.project.repository.MeetingTimeRepository;
import com.project.service.MeetingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingTimeServiceImpl implements MeetingTimeService {

    @Autowired
    private final MeetingTimeRepository meetingTimeRepository;

    public MeetingTimeServiceImpl(MeetingTimeRepository meetingTimeRepository) {
        super();
        this.meetingTimeRepository = meetingTimeRepository;
    }

    @Override
    public List<MeetingTime> getAllMeetingTime() {
        return meetingTimeRepository.findAll();
    }

    @Override
    public MeetingTime saveMeetingTime(MeetingTime meetingTime) {
        return meetingTimeRepository.save(meetingTime);
    }

    @Override
    public MeetingTime getMeetingTimeById(int id) {
        return meetingTimeRepository.findById(id);
    }

    @Override
    public MeetingTime updateMeetingTime(MeetingTime meetingTime) {
        return meetingTimeRepository.save(meetingTime);
    }

    @Override
    public void deleteMeetingTimeById(int id) {
        meetingTimeRepository.deleteById(id);
    }
}
