package com.example.proposal.mapper;

import com.example.proposal.dto.StateChangeHistoryDTO;
import com.example.proposal.enums.State;
import com.example.proposal.model.StateChangeHistoryEntity;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StateChangeHistoryMapperTest {

    @Test
    void shouldMapToDto() {
        // given
        StateChangeHistoryEntity toMap = new StateChangeHistoryEntity();
        toMap.setNextState(State.VERIFIED);
        toMap.setPreviousState(State.CREATED);
        toMap.setDate(ZonedDateTime.now());
        List<StateChangeHistoryEntity> historyToMap = List.of(toMap);

        // when
        List<StateChangeHistoryDTO> result = StateChangeHistoryMapper.toDto(historyToMap);

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(historyToMap.size());
        StateChangeHistoryDTO mapped = result.get(0);
        assertThat(mapped.getDate()).isEqualTo(toMap.getDate());
        assertThat(mapped.getNextState()).isEqualTo(toMap.getNextState());
        assertThat(mapped.getId()).isEqualTo(toMap.getId());
        assertThat(mapped.getPreviousState()).isEqualTo(toMap.getPreviousState());
    }

    @Test
    void shouldMapToNullDto() {
        // given

        // when
        List<StateChangeHistoryDTO> mapped = StateChangeHistoryMapper.toDto(null);

        // then
        assertThat(mapped).isNotNull();
        assertThat(mapped.size()).isEqualTo(0);
    }
}