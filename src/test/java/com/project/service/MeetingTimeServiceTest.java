package com.project.service;

import com.project.entity.MeetingTime;
import com.project.entity.Room;
import com.project.repository.MeetingTimeRepository;
import com.project.repository.RoomRepository;
import com.project.service.impl.MeetingTimeServiceImpl;
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
public class MeetingTimeServiceTest {
    @Mock
    private MeetingTimeRepository meetingTimeRepository;

    @InjectMocks
    private MeetingTimeServiceImpl meetingTimeService;

    private MeetingTime meetingTime;

    @BeforeEach
    public void setUp(){
        meetingTime=new MeetingTime(1,"Never 12:00-13:00");
    }

    @Test
    void getAllMeetingTimesTest(){
        MeetingTime meetingTime2=new MeetingTime(2,"Never2 12:00-13:00");
        ArrayList<MeetingTime> meetingTimes = new ArrayList<>();
        meetingTimes.add(meetingTime);
        meetingTimes.add(meetingTime2);
        given(meetingTimeRepository.findAll()).willReturn(meetingTimes);
        List<MeetingTime> meetingTimeList = meetingTimeService.getAllMeetingTime();
        assertThat(meetingTimeList).isNotNull();
        assertThat(meetingTimeList.size()).isEqualTo(2);
    }

    @Test
    void saveMeetingTimeTest(){
        given(meetingTimeRepository.save(meetingTime)).willReturn(meetingTime);

        MeetingTime savedMeetingTime =meetingTimeService.saveMeetingTime(meetingTime);

        assertThat(savedMeetingTime).isNotNull();
        assertThat(savedMeetingTime).isEqualTo(meetingTime);
    }
    @Test
    void getMeetingTimeByIdTest(){
        given(meetingTimeRepository.findById(1)).willReturn(meetingTime);

        MeetingTime savedMeetingTime = meetingTimeService.getMeetingTimeById(meetingTime.getId());

        assertThat(savedMeetingTime).isNotNull();
    }

    @Test
    public void updateMeetingTimeTest(){
        given(meetingTimeRepository.save(meetingTime)).willReturn(meetingTime);
        meetingTime.setTime("AnotherTime");

        MeetingTime updatedMeetingTime = meetingTimeService.updateMeetingTime(meetingTime);
        assertThat(updatedMeetingTime.getTime()).isEqualTo("AnotherTime");
    }

    @Test
    public void deleteMeetingTimeTest(){
        int id=1;
        willDoNothing().given(meetingTimeRepository).deleteById(id);
        meetingTimeService.deleteMeetingTimeById(1);

        verify(meetingTimeRepository,times(1)).deleteById(id);
    }
}
