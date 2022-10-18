package com.project.controller;

import com.project.scheduler.Timetable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    @GetMapping("/timetable")
    public String getSchedule(Model model) {
        new Timetable();
        model.addAttribute("timetable", Timetable.solution);
        return "timetable";
    }

}
