package com.example.proposal.dto;

import lombok.Value;

import java.util.List;

@Value
public class ProposalPageableResult {
    Integer page;
    Integer totalPages;
    List<ProposalDTO> proposals;
}
