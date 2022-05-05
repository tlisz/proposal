package com.example.proposal.service.impl;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.mapper.ProposalMapper;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.repository.ProposalRepository;
import com.example.proposal.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;

    @Override
    public List<ProposalDTO> getProposals() {
        List<ProposalEntity> all = proposalRepository.findAll();
        return all.stream().map(ProposalMapper::toDto).toList();
    }
}
