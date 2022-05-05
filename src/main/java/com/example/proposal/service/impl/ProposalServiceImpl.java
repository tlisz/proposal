package com.example.proposal.service.impl;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;
import com.example.proposal.mapper.ProposalMapper;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.repository.ProposalRepository;
import com.example.proposal.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final ProposalRepository proposalRepository;

    @Override
    public ProposalPageableResult getProposals(ProposalPageableRequest request) {
        if (request.getPageSize() == null) {
            request.setPageSize(DEFAULT_PAGE_SIZE);
        }
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getPageSize(), Sort.by(request.getSortColumn().toString().toLowerCase()));
        Page<ProposalEntity> result = proposalRepository.findAll(pageRequest);
        List<ProposalDTO> proposals = result.stream().map(ProposalMapper::toDto).toList();
        return new ProposalPageableResult(request.getPage(), result.getTotalPages(), proposals);
    }
}
