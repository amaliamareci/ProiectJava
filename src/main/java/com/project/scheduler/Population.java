package com.project.scheduler;

import com.project.entity.Course;
import com.project.entity.MeetingTime;
import com.project.entity.Professor;
import com.project.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * population class to create a number of schedules (ie to populate various partial solutions to our problem)
 */
public class Population {
    // one schedule stores a list of classes
    private final ArrayList<Schedule> schedules;

    public Population(int size, List<Course> courses, List<MeetingTime> meetingTimes, List<Room> rooms, List<Professor> professors) {
        schedules = new ArrayList<Schedule>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(courses, meetingTimes, rooms, professors).initialize()));
    }

    public ArrayList<Schedule> getSchedules() {
        return this.schedules;
    }

    public Population sortByFitness() {
        schedules.sort((schedule1, schedule2) -> {
            int returnValue = 0;
            if (schedule1.getFitness() > schedule2.getFitness()) returnValue = -1;
            else if (schedule1.getFitness() < schedule2.getFitness()) returnValue = 1;
            return returnValue;
        });
        return this;
    }

}
