package com.example.proposal.controller;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;
import com.example.proposal.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("proposal")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping("sorted")
    public ProposalPageableResult getProposals(@Valid @RequestBody ProposalPageableRequest request) {
        return proposalService.getProposals(request);
    }

    @PostMapping
    public ProposalDTO createProposal(@Valid @RequestBody ProposalDTO newProposal) {
        return proposalService.createProposal(newProposal);
    }

    @PutMapping
    public  ProposalDTO updateProposal(@Valid @RequestBody ProposalDTO newProposal) {
        return proposalService.updateProposal(newProposal);
    }
}
