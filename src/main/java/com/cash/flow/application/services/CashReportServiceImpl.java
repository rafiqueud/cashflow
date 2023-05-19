package com.cash.flow.application.services;

import com.cash.flow.adapters.inbound.dtos.DailyReportBalanceDTO;
import com.cash.flow.adapters.outbound.persistence.CashRepository;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CashReportServiceImpl implements CashReportService {
    private final CashRepository cashRepository;

    public CashReportServiceImpl(final CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public List<DailyReportBalanceDTO> generateDailyBalanceReport(final Timestamp startDate, final Timestamp endDate) {
        List<DailyReportBalanceDTO> reports = new ArrayList<>();

        List<Object[]> results = cashRepository.calculateDailyBalance(startDate, endDate);
        for (Object[] result : results) {
            final var date = (Date) result[0];
            final var balance = (BigDecimal) result[1];
            final var report = new DailyReportBalanceDTO(date.toLocalDate(), balance);
            reports.add(report);
        }

        return reports;
    }
}
