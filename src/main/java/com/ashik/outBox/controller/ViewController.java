package com.ashik.outBox.controller;

import com.ashik.outBox.entity.OutboxEvent;
import com.ashik.outBox.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/employee")
@Slf4j
public class ViewController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/view")
    public String showEventsPage(Model model) {
        List<OutboxEvent> events = employeeService.findAlls();

        // 🔥 Log the events to see if they are fetched correctly
//        log.info("Fetched {} events from the database", events.size());
//        for (OutboxEvent event : events) {
//            log.info("Event ID: {}, Type: {}, Created At: {}", event.getId(), event.getEventType(), event.getCreatedAt());
//        }
        model.addAttribute("events", events);
        return "event";
    }
}
