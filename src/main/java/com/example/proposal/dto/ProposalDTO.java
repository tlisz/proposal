package com.example.proposal.dto;

import com.example.proposal.enums.State;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProposalDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String text;

    private String reason;

    @NotNull
    @NotEmpty
    private State state;
}
