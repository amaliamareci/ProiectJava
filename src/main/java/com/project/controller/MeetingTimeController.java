package com.project.controller;

import com.project.entity.MeetingTime;
import com.project.service.MeetingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MeetingTimeController {
    @Autowired
    private MeetingTimeService meetingTimeService;

    public MeetingTimeController(MeetingTimeService meetingTimeService) {
        super();
        this.meetingTimeService = meetingTimeService;
    }

    @GetMapping("/all/meetingTimes")
    public ResponseEntity<List<MeetingTime>> getAll() {
        return new ResponseEntity<>(meetingTimeService.getAllMeetingTime(), HttpStatus.OK);
    }

    @GetMapping("/meetingTimes")
    public String listMeetingTimes(Model model) {
        model.addAttribute("meetingTimes", meetingTimeService.getAllMeetingTime());
        return "meetingTimes";
    }

    @GetMapping("/meetingTimes/new")
    public String createMeetingTimeForm(Model model) {
        MeetingTime meetingTime = new MeetingTime();
        model.addAttribute("meetingTime", meetingTime);
        return "create_meetingTime";
    }

    @PostMapping("/meetingTimes")
    public String saveMeetingTime(@ModelAttribute("meetingTime") MeetingTime meetingTime) {
        meetingTimeService.saveMeetingTime(meetingTime);
        return "redirect:/meetingTimes";
    }

    @GetMapping("/meetingTimes/edit/{id}")
    public String editMeetingTimeForm(@PathVariable int id, Model model) {
        model.addAttribute("meetingTime", meetingTimeService.getMeetingTimeById(id));
        return "edit_meetingTime";
    }

    @GetMapping("/meetingTimes/{id}")
    public String deleteMeetingTime(@PathVariable int id) {
        meetingTimeService.deleteMeetingTimeById(id);
        return "redirect:/meetingTimes";
    }

}
