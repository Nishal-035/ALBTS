package com.examly.springapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Comment;
import com.examly.springapp.model.Ticket;
import com.examly.springapp.model.User;
import com.examly.springapp.service.CommentService;
import com.examly.springapp.service.TicketService;
import com.examly.springapp.service.UserService;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private final TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Create ticket
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.createTicket(ticket), HttpStatus.CREATED);
    }

    // Get all tickets
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // Get ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    // Update ticket
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable Long id,
            @RequestBody Ticket ticket) {

        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }

    // Get tickets by status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getTicketsByStatus(@PathVariable String status) {

        List<Ticket> tickets = ticketService.getTicketsByStatus(status);

        if (tickets.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("No tickets found with status: " + status);
        }

        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{ticketId}/assign/{userId}")
    public ResponseEntity<?> assignTicket(
        @PathVariable Long ticketId,
        @PathVariable Long userId) {

    User user = userService.getUserById(userId);
    if (user == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    Ticket ticket = ticketService.assignTicketToUser(ticketId, user);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket not found");
        }

        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{ticketId}/status/{status}")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long ticketId,
            @PathVariable String status) {

        Ticket ticket = ticketService.updateTicketStatus(ticketId, status);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket not found");
        }

        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/{ticketId}/comments")
    public ResponseEntity<?> addComment(
            @PathVariable Long ticketId,
            @RequestBody Map<String, String> request) {

        Comment comment = commentService.addCommentToTicket(
                ticketId,
                request.get("content")
        );

        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket not found");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUser(@PathVariable Long userId) {
        return ticketService.getTicketsByUser(userId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Ticket> getTicketsByCategory(@PathVariable Long categoryId) {
        return ticketService.getTicketsByCategory(categoryId);
    }

    @GetMapping("/project/{projectId}")
    public List<Ticket> getTicketsByProject(@PathVariable Long projectId) {
        return ticketService.getTicketsByProject(projectId);
    }

    @GetMapping("/filter/status/{status}/category/{categoryId}")
    public List<Ticket> getTicketsByStatusAndCategory(
            @PathVariable String status,
            @PathVariable Long categoryId) {
        return ticketService.getTicketsByStatusAndCategory(status, categoryId);
    }

    @GetMapping("/filter/status/{status}/project/{projectId}")
    public List<Ticket> getTicketsByStatusAndProject(
            @PathVariable String status,
            @PathVariable Long projectId) {
        return ticketService.getTicketsByStatusAndProject(status, projectId);
    }

    @GetMapping("/filter/user/{userId}/project/{projectId}")
    public List<Ticket> getTicketsByUserAndProject(
            @PathVariable Long userId,
            @PathVariable Long projectId) {
        return ticketService.getTicketsByUserAndProject(userId, projectId);
    }

    @GetMapping("/report/summary")
    public List<Object[]> getTicketSummaryReport() {
        return ticketService.getTicketSummaryReport();
    }

}
