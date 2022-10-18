package com.project.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Class {
    private int id;
    private Course course;
    private Professor professor;
    private MeetingTime meetingTime;
    private Room room;

    public Class() {
        this.id = -1;
        this.course = null;
    }

    public Class(int id, Course course) {
        this.id = id;
        this.course = course;
    }

    public String toString() {
        return "[" + course.getId() + "," + room.getId() + "," + professor.getId() + "," + meetingTime.getId() + "]";
    }
}
