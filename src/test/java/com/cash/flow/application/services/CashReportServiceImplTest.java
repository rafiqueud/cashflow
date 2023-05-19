package com.cash.flow.application.services;

import com.cash.flow.adapters.outbound.persistence.CashRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CashReportServiceImplTest {

    @InjectMocks
    private CashReportServiceImpl cashReportService;

    @Mock
    private CashRepository cashRepository;

    @Test
    void generateDailyBalanceReport() {

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 31);


        List<Object[]> transactions = new ArrayList<>();
        transactions.add(new Object[]{Date.valueOf(startDate), BigDecimal.TEN});
        transactions.add(new Object[]{Date.valueOf(startDate.plusDays(1)), BigDecimal.TEN.add(BigDecimal.ONE)});
        transactions.add(new Object[]{Date.valueOf(startDate.plusDays(2)), BigDecimal.TEN.add(BigDecimal.TEN)});


        Mockito.when(cashRepository.calculateDailyBalance(any(), any())).thenReturn(transactions);

        final var result = cashReportService.generateDailyBalanceReport(Timestamp.valueOf(startDate.atStartOfDay()), Timestamp.valueOf(endDate.atStartOfDay()));

        Assertions.assertEquals(BigDecimal.TEN, result.get(0).getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(11), result.get(1).getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(20), result.get(2).getBalance());
    }
}