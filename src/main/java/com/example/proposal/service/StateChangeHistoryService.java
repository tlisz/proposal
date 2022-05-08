package com.example.proposal.service;

import com.example.proposal.dto.StateChangeHistoryDTO;

import java.util.List;

public interface StateChangeHistoryService {

    List<StateChangeHistoryDTO> findByProposalId(Long proposalId);
}
