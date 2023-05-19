package com.cash.flow.core.entities;

import com.cash.flow.core.enums.CashType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
public class Cash {

    @Id
    private UUID id;
    private String description;

    @Enumerated(EnumType.STRING)
    private CashType type;
    private BigDecimal amount;
    private Timestamp date;

    public Cash() {
        this.id = UUID.randomUUID();
        this.date = Timestamp.from(Instant.now());
    }

    public Cash(UUID id, String description, CashType type, BigDecimal amount, Timestamp date) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CashType getType() {
        return type;
    }

    public void setType(CashType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
