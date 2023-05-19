package com.cash.flow.adapters.outbound.persistence;

import com.cash.flow.core.entities.Cash;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepository extends JpaRepository<Cash, UUID> {

    @Query("SELECT DATE(DATE_TRUNC(day, c.date)) AS date, SUM(CASE WHEN c.type = 'IN' THEN c.amount ELSE -c.amount END) AS balance " +
            "FROM Cash c " +
            "WHERE DATE(c.date) >= :startDate AND DATE(c.date) <= :endDate " +
            "GROUP BY DATE(DATE_TRUNC(day, c.date)) " +
            "ORDER BY DATE(DATE_TRUNC(day, c.date)) ")
    List<Object[]> calculateDailyBalance(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}
