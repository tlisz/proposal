package com.example.proposal.service.impl;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;
import com.example.proposal.enums.State;
import com.example.proposal.exception.ProposalRequestException;
import com.example.proposal.mapper.ProposalMapper;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.repository.ProposalRepository;
import com.example.proposal.service.ProposalService;
import com.example.proposal.validator.UpdateValidatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public ProposalDTO createProposal(ProposalDTO newProposal) {
        if (isRequestInvalid(newProposal)) {
            String message = "Wrong request for proposal creation";
            log.warn(message + " " + newProposal);
            throw new ProposalRequestException(message);
        }
        return ProposalMapper.toDto(proposalRepository.save(ProposalMapper.toEntity(newProposal)));
    }

    @Override
    public ProposalDTO updateProposal(ProposalDTO proposalToUpdate) {
        if (isUpdateInvalid(proposalToUpdate)) {
            String message = "Wrong request for proposal update";
            log.warn(message + " " + proposalToUpdate);
            throw new ProposalRequestException(message);
        }

        return ProposalMapper.toDto(proposalRepository.save(ProposalMapper.toEntity(proposalToUpdate)));
    }

    private boolean isUpdateInvalid(ProposalDTO proposalToUpdate) {
        if (proposalToUpdate.getId() == null) {
            return true;
        }
        Optional<ProposalEntity> currentProposal = proposalRepository.findById(proposalToUpdate.getId());
        return !(currentProposal.isPresent() && isUpdateValid(proposalToUpdate, currentProposal.get()));
    }

    private boolean isUpdateValid(ProposalDTO proposalToUpdate, ProposalEntity currentProposal) {
        return UpdateValidatorUtil.validate(currentProposal, proposalToUpdate);
    }

    private boolean isRequestInvalid(ProposalDTO newProposal) {
        return newProposal.getId() != null || !newProposal.getState().equals(State.CREATED) || newProposal.getReason() != null;
    }
}
