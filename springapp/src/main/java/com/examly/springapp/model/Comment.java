package com.examly.springapp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
}
