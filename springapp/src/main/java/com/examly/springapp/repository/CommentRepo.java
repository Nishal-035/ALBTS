package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByTicketTicketId(Long ticketId);
}
