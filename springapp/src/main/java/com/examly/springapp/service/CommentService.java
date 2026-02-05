package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.BadRequestException;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Comment;
import com.examly.springapp.model.Ticket;
import com.examly.springapp.repository.CommentRepo;
import com.examly.springapp.repository.TicketRepo;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private TicketRepo ticketRepo;

    
    public Comment addCommentToTicket(Long ticketId, String content) {

    if (content == null || content.trim().isEmpty()) {
        throw new BadRequestException("Comment content cannot be empty");
    }

    Ticket ticket = ticketRepo.findById(ticketId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Ticket not found with id: " + ticketId));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTicket(ticket);

        return commentRepo.save(comment);
    }
}
