package com.cash.flow.adapters.inbound.dtos;

import com.cash.flow.core.enums.CashType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class CashDTO {
    private UUID id;
    private String description;
    private CashType type;
    private BigDecimal amount;
    private Timestamp date;

    public CashDTO() {
    }

    // Getters and Setters

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashDTO cashDTO = (CashDTO) o;
        return Objects.equals(id, cashDTO.id) && Objects.equals(description, cashDTO.description) && type == cashDTO.type && Objects.equals(amount, cashDTO.amount) && Objects.equals(date, cashDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, type, amount, date);
    }
}
