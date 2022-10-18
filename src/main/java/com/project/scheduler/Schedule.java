package com.project.scheduler;

import java.util.ArrayList;
import java.util.List;

import com.project.entity.*;
import com.project.entity.Class;

public class Schedule {
    private final ArrayList<Class> classes;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int classNumb = 0;
    private int numbOfConflicts = 0;
    private final List<Course> courses;
    private final List<MeetingTime> meetingTimes;
    private final List<Room> rooms;
    private final List<Professor> professors;

    public Schedule(List<Course> courses, List<MeetingTime> meetingTimes, List<Room> rooms, List<Professor> professors) {
        classes = new ArrayList<>();
        this.courses = courses;
        this.meetingTimes = meetingTimes;
        this.rooms = rooms;
        this.professors = professors;
    }

    /**
     * initializes the schedule with random values
     */
    public Schedule initialize() {
        new ArrayList<Course>(courses).forEach(course -> {
            Class newClass = new Class(classNumb++, course);
            newClass.setMeetingTime(meetingTimes.get((int) (meetingTimes.size() * Math.random())));
            newClass.setRoom(rooms.get((int) (rooms.size() * Math.random())));
            newClass.setProfessor(professors.get((int) (professors.size() * Math.random())));
            classes.add(newClass);
        });
        return this;
    }

    public ArrayList<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness() {
        if (isFitnessChanged) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    /**
     * computes the number of conflicts and return the fitness
     *      (fitness is 1 / (1 + conflicts))
     */
    private double calculateFitness() {
        numbOfConflicts = 0;
        classes.forEach(x -> {
            if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumberOfStudents() || (x.getRoom().getSeatingCapacity() > 100 && x.getCourse().getMaxNumberOfStudents() < 50))//groupsize
                numbOfConflicts++;
            if (!x.getCourse().getProfessors().contains(x.getProfessor())) numbOfConflicts++;
            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) {
                    if (x.getRoom() == y.getRoom()) numbOfConflicts++;
                    if (x.getProfessor() == y.getProfessor()) numbOfConflicts++;
                }
            });
        });
        return 1 / (double) (numbOfConflicts + 1);
    }

}
