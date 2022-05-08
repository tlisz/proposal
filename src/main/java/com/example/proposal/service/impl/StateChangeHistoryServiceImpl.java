package com.example.proposal.service.impl;

import com.example.proposal.dto.StateChangeHistoryDTO;
import com.example.proposal.mapper.StateChangeHistoryMapper;
import com.example.proposal.repository.StateChangeHistoryRepository;
import com.example.proposal.service.StateChangeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateChangeHistoryServiceImpl implements StateChangeHistoryService {

    private final StateChangeHistoryRepository repository;

    @Override
    public List<StateChangeHistoryDTO> findByProposalId(Long proposalId) {
        return StateChangeHistoryMapper.toDto(repository.findByProposalId(proposalId));
    }
}
