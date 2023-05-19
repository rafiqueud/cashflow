package com.cash.flow.application.services;

import com.cash.flow.adapters.inbound.dtos.CashDTO;
import com.cash.flow.adapters.inbound.dtos.CreateCashDTO;
import com.cash.flow.adapters.inbound.mappers.CashMapper;
import com.cash.flow.adapters.outbound.persistence.CashRepository;
import com.cash.flow.core.entities.Cash;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CashService {
    private final CashRepository cashRepository;
    private final CashMapper cashMapper;

    public CashService(CashRepository cashRepository, CashMapper cashMapper) {
        this.cashRepository = cashRepository;
        this.cashMapper = cashMapper;
    }

    public void createCash(CreateCashDTO createCashDTO) {
        Cash cash = cashMapper.mapToCash(createCashDTO);
        cashRepository.save(cash);
    }

    public CashDTO getCashById(UUID cashId) {
        Optional<Cash> cashOptional = cashRepository.findById(cashId);
        if (cashOptional.isPresent()) {
            Cash cash = cashOptional.get();
            return cashMapper.mapToCashDTO(cash);
        } else {
            return null;
        }
    }

    public List<CashDTO> getPaginatedCash(int page, int size) {
        final var pageable = PageRequest.of(page, size);
        final var cashPage = cashRepository.findAll(pageable);
        return cashMapper.mapToCashDTOList(cashPage.getContent());
    }

    // Outros métodos do serviço para atualizar, excluir ou realizar outras operações relacionadas ao fluxo de caixa
}
