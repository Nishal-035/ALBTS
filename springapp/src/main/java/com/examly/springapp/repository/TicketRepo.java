package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.examly.springapp.model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(String status);
    List<Ticket> findByUserUserId(Long userId);
    List<Ticket> findByCategoryCategoryId(Long categoryId);
    List<Ticket> findByProjectProjectId(Long projectId);

    @Query("SELECT t FROM Ticket t WHERE t.status = :status")
    List<Ticket> findTicketsByStatusJPQL(@Param("status") String status);

    // 1 Tickets by Status + Category
    @Query("SELECT t FROM Ticket t WHERE t.status = :status AND t.category.categoryId = :categoryId")
    List<Ticket> findTicketsByStatusAndCategory(
            @Param("status") String status,
            @Param("categoryId") Long categoryId);

    // 2 Tickets by Status + Project
    @Query("SELECT t FROM Ticket t WHERE t.status = :status AND t.project.projectId = :projectId")
    List<Ticket> findTicketsByStatusAndProject(
            @Param("status") String status,
            @Param("projectId") Long projectId);

    // 3 Tickets raised by a User in a Project
    @Query("SELECT t FROM Ticket t WHERE t.user.userId = :userId AND t.project.projectId = :projectId")
    List<Ticket> findTicketsByUserAndProject(
            @Param("userId") Long userId,
            @Param("projectId") Long projectId);

    // 4 Report Query (Partial fields)
    @Query("SELECT t FROM Ticket t")
    List<Object[]> getTicketSummaryReport();
}
