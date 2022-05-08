package com.example.proposal.service.impl;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;
import com.example.proposal.enums.State;
import com.example.proposal.exception.ProposalRequestException;
import com.example.proposal.mapper.ProposalMapper;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.model.StateChangeHistoryEntity;
import com.example.proposal.repository.ProposalRepository;
import com.example.proposal.repository.StateChangeHistoryRepository;
import com.example.proposal.service.ProposalService;
import com.example.proposal.validator.UpdateValidatorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProposalServiceImpl implements ProposalService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final ProposalRepository proposalRepository;
    private final StateChangeHistoryRepository stateChangeHistoryRepository;

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
    @Transactional
    public ProposalDTO createProposal(ProposalDTO newProposal) {
        if (isRequestInvalid(newProposal)) {
            String message = "Wrong request for proposal creation";
            log.warn(message + " " + newProposal);
            throw new ProposalRequestException(message);
        }

        ProposalEntity savedProposal = proposalRepository.save(ProposalMapper.toEntity(newProposal));
        stateChangeHistoryRepository.save(createStateChange(savedProposal));
        return ProposalMapper.toDto(savedProposal);
    }

    @Override
    @Transactional
    public ProposalDTO updateProposal(ProposalDTO proposalToUpdate) {
        Optional<ProposalEntity> currentProposal = proposalRepository.findById(proposalToUpdate.getId());
        if (currentProposal.isEmpty() || isUpdateInvalid(proposalToUpdate, currentProposal.get())) {
            String message = "Wrong request for proposal update";
            log.warn(message + " " + proposalToUpdate);
            throw new ProposalRequestException(message);
        }

        ProposalEntity savedProposal = proposalRepository.save(ProposalMapper.toEntity(proposalToUpdate));
        stateChangeHistoryRepository.save(createStateChange(savedProposal, currentProposal.get().getState()));
        return ProposalMapper.toDto(savedProposal);
    }

    private StateChangeHistoryEntity createStateChange(ProposalEntity savedProposal) {
        return createStateChange(savedProposal, null);
    }

    private StateChangeHistoryEntity createStateChange(ProposalEntity savedProposal, State previousState) {
        StateChangeHistoryEntity stateChange = new StateChangeHistoryEntity();
        stateChange.setPreviousState(previousState);
        stateChange.setNextState(savedProposal.getState());
        stateChange.setProposal(savedProposal);
        stateChange.setDate(ZonedDateTime.now());
        return stateChange;
    }

    private boolean isUpdateInvalid(ProposalDTO proposalToUpdate, ProposalEntity currentProposal) {
        if (proposalToUpdate.getId() == null) {
            return true;
        }
        return !isUpdateValid(proposalToUpdate, currentProposal);
    }

    private boolean isUpdateValid(ProposalDTO proposalToUpdate, ProposalEntity currentProposal) {
        return UpdateValidatorUtil.validate(currentProposal, proposalToUpdate);
    }

    private boolean isRequestInvalid(ProposalDTO newProposal) {
        return newProposal.getId() != null || !newProposal.getState().equals(State.CREATED) || newProposal.getReason() != null;
    }
}
