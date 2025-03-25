package com.ashik.outBox.controller;


import com.ashik.outBox.entity.Employee;
import com.ashik.outBox.entity.OutboxEvent;
import com.ashik.outBox.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/api")
    public ResponseEntity<List<OutboxEvent>> getAllEvents() {
        return employeeService.findAll();
    }

//    @GetMapping("/view")
//    public String showEventsPage(Model model) {
//        List<OutboxEvent> events = employeeService.findAlls();
//        model.addAttribute("events", events);
//        return "event";
//    }

}
