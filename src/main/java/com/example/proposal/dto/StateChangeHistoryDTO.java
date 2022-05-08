package com.example.proposal.dto;

import com.example.proposal.enums.State;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class StateChangeHistoryDTO {

    private Long id;
    private State previousState;
    private State nextState;
    private ZonedDateTime date;
}
