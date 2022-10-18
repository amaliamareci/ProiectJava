package com.project.scheduler;

import com.project.entity.Course;
import com.project.entity.MeetingTime;
import com.project.entity.Professor;
import com.project.entity.Room;

import java.util.List;
import java.util.stream.IntStream;

public class GeneticAlgorithm implements Evolution {
    private final List<Course> courses;
    private final List<MeetingTime> meetingTimes;
    private final List<Room> rooms;
    private final List<Professor> professors;

    public GeneticAlgorithm(List<Course> courses, List<MeetingTime> meetingTimes, List<Room> rooms, List<Professor> professors) {
        this.courses = courses;
        this.meetingTimes = meetingTimes;
        this.rooms = rooms;
        this.professors = professors;
    }

    /**
     * Make a mutation on a crossover population
     */
    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    /**
     * makes a population and places on the first two positions
     *      the best two schedules from the previous generation.
     * Then replaces the next schedules (with a probability of Population.CROSSOVER_RATE)
     *      with a crossover between two random selected schedules from the previous population.
     */
    public Population crossoverPopulation(Population population) {
        Population crossoverPopulation = new Population(population.getSchedules().size(), courses, meetingTimes, rooms, professors);
        IntStream.range(0, Timetable.NUMB_OF_ELITE_SCHEDULES).forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));
        IntStream.range(Timetable.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
            if (Timetable.CROSSOVER_RATE > Math.random()) {
                Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
            } else {
                crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
            }
        });
        return crossoverPopulation;
    }

    /**
     * makes a schedule by random selecting classes from two schedules
     */
    public Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) {
        Schedule crossoverSchedule = new Schedule(courses, meetingTimes, rooms, professors).initialize();
        IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x -> {
            if (Math.random() > 0.5) crossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
            else crossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
        });
        return crossoverSchedule;
    }

    /**
     * Places on the first two positions the best two schedules,
     *      and then applies mutation on the rest of the population
     */
    public Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.getSchedules().size(), courses, meetingTimes, rooms, professors);
        IntStream.range(0, Timetable.NUMB_OF_ELITE_SCHEDULES).forEach(x -> mutatePopulation.getSchedules().set(x, population.getSchedules().get(x)));
        IntStream.range(Timetable.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
            mutatePopulation.getSchedules().set(x, mutateSchedule(population.getSchedules().get(x)));
        });
        return mutatePopulation;
    }

    /**
     * with a chance of Timetable.MUTATION_RATE replace a class from mutateSchedule with a random one
     */
    public Schedule mutateSchedule(Schedule mutateSchedule) {
        Schedule schedule = new Schedule(courses, meetingTimes, rooms, professors).initialize();
        IntStream.range(0, mutateSchedule.getClasses().size()).forEach(x -> {
            if (Timetable.MUTATION_RATE > Math.random())
                mutateSchedule.getClasses().set(x, schedule.getClasses().get(x));
        });
        return mutateSchedule;
    }

    /**
     * takes Timetable.TOURNAMENT_SELECTION_SIZE random schedules
     *      from population and returns a population with them
     */
    public Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(Timetable.TOURNAMENT_SELECTION_SIZE, courses, meetingTimes, rooms, professors);
        IntStream.range(0, Timetable.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
            tournamentPopulation.getSchedules().set(x, population.getSchedules().get((int) (Math.random() * population.getSchedules().size())));
        });
        return tournamentPopulation;
    }
}
