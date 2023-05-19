package com.cash.flow.adapters.inbound.controllers;

import com.cash.flow.adapters.inbound.dtos.CashDTO;
import com.cash.flow.adapters.inbound.dtos.CreateCashDTO;
import com.cash.flow.application.services.CashService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class CashController {
    private final CashService cashService;

    public CashController(final CashService cashService) {
        this.cashService = cashService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody CreateCashDTO createCashDTO) {
        cashService.createCash(createCashDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cashId}")
    public ResponseEntity<CashDTO> getTransaction(@PathVariable("cashId") UUID cashId) {
        final var cashDTO = cashService.getCashById(cashId);
        if (cashDTO != null) {
            return ResponseEntity.ok(cashDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CashDTO>> getPaginatedCash(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<CashDTO> cashList = cashService.getPaginatedCash(page, size);
        return ResponseEntity.ok(cashList);
    }
}
