package com.project.timetable;

import com.project.controller.CourseController;
import com.project.entity.Class;
import com.project.entity.Course;
import com.project.entity.MeetingTime;
import com.project.entity.Professor;
import com.project.entity.Room;
import com.project.scheduler.Timetable;
import com.project.service.MeetingTimeService;
import com.project.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AlgorithmTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private Timetable timetable;
    private final String uri = "http://localhost:8088/all";
    public static List<Class> solution;

    @Test
    public void simpleScheduler() {
        List<Course> courses =new ArrayList<>();
        courses.add(new Course(1,"CursTest",100));
        Course[] responseCourses = new Course[1];
        responseCourses[0]=courses.get(0);

        List<MeetingTime> meetingTimes=new ArrayList<>();
        meetingTimes.add(new MeetingTime(1,"MeetingTimeTest"));
        MeetingTime[] responseMeetingTime=new MeetingTime[1];
        responseMeetingTime[0]=meetingTimes.get(0);

        List<Room> rooms =new ArrayList<>();
        rooms.add(new Room(1,"RoomTest",200));
        Room[] responseRoom =new Room[1];
        responseRoom[0]=rooms.get(0);

        List<Professor> professors=new ArrayList<>();
        professors.add(new Professor(1,"ProfessorTest"));
        Professor[] responseProfessor=new Professor[1];
        responseProfessor[0]=professors.get(0);

        when(restTemplate.getForObject(uri + "/courses", Course[].class)).thenReturn(responseCourses);
        given(restTemplate.getForObject(uri + "/courses", Course[].class)).willReturn(responseCourses);
        System.out.println(Arrays.toString(restTemplate.getForObject(uri + "/courses", Course[].class)));
       // Timetable timetable =new Timetable();
        //timetable =new Timetable();
        //List<Class> expectedSolution = [[3,2,2,5], [4,5,4,3], [5,2,2,4]];
        solution=Timetable.solution;
        System.out.println("?????????????????"+solution);

    }
}
