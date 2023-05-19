package com.cash.flow.adapters.inbound.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyReportBalanceDTO {

    private LocalDate date;
    private BigDecimal balance;

    public DailyReportBalanceDTO() {
    }

    public DailyReportBalanceDTO(LocalDate date, BigDecimal balance) {
        this.date = date;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
