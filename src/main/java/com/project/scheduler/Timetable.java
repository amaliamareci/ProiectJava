package com.project.scheduler;

import com.project.entity.Class;
import com.project.entity.Course;
import com.project.entity.MeetingTime;
import com.project.entity.Professor;
import com.project.entity.Room;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class Timetable {

    public static final int POPULATION_SIZE = 20;
    public static final double MUTATION_RATE = 0.3;
    public static final double CROSSOVER_RATE = 0.8;
    public static final int TOURNAMENT_SELECTION_SIZE = 6;
    public static final int NUMB_OF_ELITE_SCHEDULES = 2;
    private final String uri = "http://proiect-pa-orar.herokuapp.com/all";

    public static List<Class> solution;

    public Timetable() {
        init();
    }

    public void init() {
        RestTemplate restTemplate = new RestTemplate();

        Course[] responseCourses = restTemplate.getForObject(uri + "/courses", Course[].class);
        List<Course> courses = Arrays.asList(responseCourses);

        MeetingTime[] responseMeetingTimes = restTemplate.getForObject(uri + "/meetingTimes", MeetingTime[].class);
        List<MeetingTime> meetingTimes = Arrays.asList(responseMeetingTimes);

        Room[] responseRooms = restTemplate.getForObject(uri + "/rooms", Room[].class);
        List<Room> rooms = Arrays.asList(responseRooms);

        Professor[] responseProfessors = restTemplate.getForObject(uri + "/professors", Professor[].class);
        List<Professor> professors = Arrays.asList(responseProfessors);

        int generationNumber = 0;

        // prepare for the first generation
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(courses, meetingTimes, rooms, professors);
        Population population = new Population(POPULATION_SIZE, courses, meetingTimes, rooms, professors).sortByFitness();

        checkSolution(population.getSchedules().get(0));

        // evolve each generation until we find one with no conflicts
        while (population.getSchedules().get(0).getFitness() != 1.0 && generationNumber < 1000) {
            generationNumber++;
            population = geneticAlgorithm.evolve(population).sortByFitness();
            checkSolution(population.getSchedules().get(0));
        }
    }

    private void checkSolution(Schedule schedule) {
        if (schedule.getFitness() == 1) {
            solution = schedule.getClasses();
        }
    }

}
