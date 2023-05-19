package com.cash.flow.adapters.inbound.mappers;

import com.cash.flow.adapters.inbound.dtos.CashDTO;
import com.cash.flow.adapters.inbound.dtos.CreateCashDTO;
import com.cash.flow.core.entities.Cash;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CashMapper {
    CashDTO mapToCashDTO(Cash cash);

    List<CashDTO> mapToCashDTOList(List<Cash> cashList);

    @Mapping(source = "date", target = "date", qualifiedByName = "toTimestamp")
    Cash mapToCash(CreateCashDTO createCashDTO);

    @Named("toTimestamp")
    static Timestamp toTimestamp(LocalDate date) {
        return date != null ? Timestamp.valueOf(date.atStartOfDay()) : null;
    }
}