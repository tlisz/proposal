package com.example.proposal.mapper;

import com.example.proposal.dto.StateChangeHistoryDTO;
import com.example.proposal.model.StateChangeHistoryEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateChangeHistoryMapper {

    private StateChangeHistoryMapper() {
        throw new IllegalStateException("Util class");
    }

    public static List<StateChangeHistoryDTO> toDto(List<StateChangeHistoryEntity> toMap) {
        if (toMap == null) {
            return Collections.emptyList();
        }

        List<StateChangeHistoryDTO> result = new ArrayList<>();

        for (StateChangeHistoryEntity entity : toMap) {
            StateChangeHistoryDTO mapped = new StateChangeHistoryDTO();
            mapped.setNextState(entity.getNextState());
            mapped.setPreviousState(entity.getPreviousState());
            mapped.setDate(entity.getDate());
            mapped.setId(entity.getId());
            result.add(mapped);
        }
        return result;
    }
}
