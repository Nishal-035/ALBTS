package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @RequestMapping("/{id}")
    public void getTicketById(@PathVariable Long id) {
    }
}
