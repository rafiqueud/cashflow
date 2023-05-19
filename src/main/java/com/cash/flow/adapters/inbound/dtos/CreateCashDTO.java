package com.cash.flow.adapters.inbound.dtos;

import com.cash.flow.core.enums.CashType;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateCashDTO {
    private String description;
    private CashType type;
    private BigDecimal amount;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
