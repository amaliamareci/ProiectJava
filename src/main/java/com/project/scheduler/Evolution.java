package com.project.scheduler;

/**
 * interface for methods of genetic algorithm
 */
public interface Evolution {
    Population evolve(Population population);

    Population crossoverPopulation(Population population);

    Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2);

    Population mutatePopulation(Population population);

    Schedule mutateSchedule(Schedule mutateSchedule);

    Population selectTournamentPopulation(Population population);
}
