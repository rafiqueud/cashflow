package com.cash.flow.application.services;

import com.cash.flow.adapters.inbound.dtos.DailyReportBalanceDTO;
import java.sql.Timestamp;
import java.util.List;

public interface CashReportService {
    List<DailyReportBalanceDTO> generateDailyBalanceReport(Timestamp startDate, Timestamp endDate);

}
