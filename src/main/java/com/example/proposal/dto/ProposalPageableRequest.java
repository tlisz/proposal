package com.example.proposal.dto;

import com.example.proposal.enums.ProposalSortColumn;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ProposalPageableRequest {

    @NotNull
    @Min(value = 0L)
    private Integer page;
    @Min(value = 0L)
    private Integer pageSize;
    @NotNull
    private ProposalSortColumn sortColumn;
}
