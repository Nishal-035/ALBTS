package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Ticket;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.TicketRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class TicketService {

    private final TicketRepo ticketRepo;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    private static final Logger logger =
        LoggerFactory.getLogger(TicketService.class);


    // Create ticket
    public Ticket createTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    // Get all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    // Get ticket by ID
    public Ticket getTicketById(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    // Update ticket
    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setSubject(updatedTicket.getSubject());
        existingTicket.setDescription(updatedTicket.getDescription());
        existingTicket.setStatus(updatedTicket.getStatus());
        existingTicket.setAssignedTo(updatedTicket.getAssignedTo());
        existingTicket.setProject(updatedTicket.getProject());
        existingTicket.setCategory(updatedTicket.getCategory());
        return ticketRepo.save(existingTicket);
    }

    // Get tickets by status
    public List<Ticket> getTicketsByStatus(String status) {
        return ticketRepo.findTicketsByStatusJPQL(status);
    }

    public Ticket assignTicketToUser(Long ticketId, User user) {
        Ticket ticket = ticketRepo.findById(ticketId).orElse(null);
        if (ticket == null) return null;

        ticket.setUser(user);
        ticket.setAssignedTo(user.getUsername());
        return ticketRepo.save(ticket);
    }

    public Ticket updateTicketStatus(Long ticketId, String status) {

    logger.info("Updating status of ticket {} to {}", ticketId, status);

    Ticket ticket = ticketRepo.findById(ticketId)
            .orElseThrow(() -> {
                logger.warn("Ticket not found with id {}", ticketId);
                    return new ResourceNotFoundException(
                            "Ticket not found with id: " + ticketId
                    );
                });

        ticket.setStatus(status);

        Ticket updatedTicket = ticketRepo.save(ticket);
        logger.info("Ticket {} status updated successfully", ticketId);

        return updatedTicket;
    }


    public List<Ticket> getTicketsByUser(Long userId) {
        return ticketRepo.findByUserUserId(userId);
    }

    public List<Ticket> getTicketsByCategory(Long categoryId) {
        return ticketRepo.findByCategoryCategoryId(categoryId);
    }

    public List<Ticket> getTicketsByProject(Long projectId) {
        return ticketRepo.findByProjectProjectId(projectId);
    }

    public List<Ticket> getTicketsByStatusAndCategory(String status, Long categoryId) {
        return ticketRepo.findTicketsByStatusAndCategory(status, categoryId);
    }

    public List<Ticket> getTicketsByStatusAndProject(String status, Long projectId) {
        return ticketRepo.findTicketsByStatusAndProject(status, projectId);
    }

    public List<Ticket> getTicketsByUserAndProject(Long userId, Long projectId) {
        return ticketRepo.findTicketsByUserAndProject(userId, projectId);
    }

    public List<Object[]> getTicketSummaryReport() {
        return ticketRepo.getTicketSummaryReport();
    }

}
