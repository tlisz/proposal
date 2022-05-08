package com.example.proposal.controller;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;
import com.example.proposal.dto.StateChangeHistoryDTO;
import com.example.proposal.service.ProposalService;
import com.example.proposal.service.StateChangeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("proposal")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;
    private final StateChangeHistoryService stateChangeHistoryService;

    @PostMapping("sorted")
    public ProposalPageableResult getProposals(@Valid @RequestBody ProposalPageableRequest request) {
        return proposalService.getProposals(request);
    }

    @PostMapping
    public ProposalDTO createProposal(@Valid @RequestBody ProposalDTO newProposal) {
        return proposalService.createProposal(newProposal);
    }

    @PutMapping("{proposalId}")
    public  ProposalDTO updateProposal(@Valid @RequestBody ProposalDTO newProposal) {
        return proposalService.updateProposal(newProposal);
    }

    @GetMapping("{proposalId}/history")
    public List<StateChangeHistoryDTO> getProposalHistory(@PathVariable Long proposalId) {
        return stateChangeHistoryService.findByProposalId(proposalId);
    }
}
