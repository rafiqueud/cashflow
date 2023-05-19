package com.cash.flow.integration;

import com.cash.flow.adapters.inbound.dtos.CashDTO;
import com.cash.flow.adapters.inbound.dtos.CreateCashDTO;
import com.cash.flow.adapters.inbound.dtos.DailyReportBalanceDTO;
import com.cash.flow.core.enums.CashType;
import com.cash.flow.utils.AbstractTestContainers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class FlowApplicationIntegrationTests extends AbstractTestContainers {

    @LocalServerPort
    private Integer port;

    private String uri;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeEach
    public void setup() {
        uri = "http://localhost:" + port + "/api";
    }

    @Test
    public void testCreateNewInboundsTypeIN() {
        // Prepare the request body
        final var cashDTO = new CreateCashDTO();
        cashDTO.setDescription("Inbound transaction");
        cashDTO.setType(CashType.IN);
        cashDTO.setAmount(BigDecimal.valueOf(100.00));
        cashDTO.setDate(LocalDate.now());

        // Send a POST request to create a new inbound transaction
        ResponseEntity<String> response = testRestTemplate.postForEntity(uri + "/transactions", cashDTO, String.class);

        // Assert the response status
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testCreateNewInboundsTypeOUT() {
        // Prepare the request body
        final var cashDTO = new CreateCashDTO();
        cashDTO.setDescription("Outbound transaction");
        cashDTO.setType(CashType.OUT);
        cashDTO.setAmount(BigDecimal.valueOf(50.00));
        cashDTO.setDate(LocalDate.now());

        // Send a POST request to create a new outbound transaction
        ResponseEntity<String> response = testRestTemplate.postForEntity(uri + "/transactions", cashDTO, String.class);

        // Assert the response status
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testRetrieveTransactionsAndGetById() {
        // Arrange
        testCreateNewInboundsTypeOUT();
        testCreateNewInboundsTypeIN();

        // Act Send a GET request to retrieve the list of transactions
        final var responseType = new ParameterizedTypeReference<List<CashDTO>>() {
        };
        final var response = testRestTemplate.exchange(uri + "/transactions", HttpMethod.GET, null, responseType);

        // Assert the response status
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Retrieve the transactions from the response body
        final var transactions = response.getBody();
        Assertions.assertNotNull(transactions);

        // Assert that the list is not empty (assuming there are existing transactions in the database)
        Assertions.assertFalse(transactions.isEmpty());
        Assertions.assertTrue(transactions.size() >= 2);

        final var transactionId = transactions.get(0).getId();

        final var getResponse = testRestTemplate.exchange(uri + "/transactions/" + transactionId, HttpMethod.GET, null, CashDTO.class);

        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        Assertions.assertEquals(transactions.get(0), getResponse.getBody());

    }

    @Test
    public void testConsolidatedReport() {
        // Arrange
        testCreateNewInboundsTypeOUT();
        testCreateNewInboundsTypeIN();
        final var responseType = new ParameterizedTypeReference<List<DailyReportBalanceDTO>>() {
        };
        // Act Send a GET request to retrieve the consolidated report of daily balances
        final var response = testRestTemplate.exchange(uri + "/cash-report/daily-balance?startDate=2020-01-01&endDate=2025-01-01", HttpMethod.GET, null, responseType);

        // Assert the response status
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // Retrieve the report from the response body
        final var report = response.getBody();
        Assertions.assertNotNull(report);

        // Assert that the report is not empty (assuming there are existing transactions in the database)
        Assertions.assertFalse(report.isEmpty());
    }
}

