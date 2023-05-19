package com.cash.flow.adapters.inbound.controllers;

import com.cash.flow.adapters.inbound.dtos.DailyReportBalanceDTO;
import com.cash.flow.application.services.CashReportService;
import io.micrometer.core.annotation.Timed;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cash-report")
public class CashReportController {

    private final CashReportService cashReportService;

    public CashReportController(CashReportService cashReportService) {
        this.cashReportService = cashReportService;
    }

    @GetMapping("/daily-balance")
    @Timed("getDailyBalanceReport")
    public ResponseEntity<List<DailyReportBalanceDTO>> getDailyBalanceReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        final var report = cashReportService.generateDailyBalanceReport(
                Timestamp.valueOf(startDate.atStartOfDay()),
                Timestamp.valueOf(endDate.plusDays(1).atStartOfDay().plusNanos(-1)));
        return ResponseEntity.ok(report);
    }
}
