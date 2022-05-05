package com.example.proposal.controller;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("proposal")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @GetMapping
    public List<ProposalDTO> getProposals() {
        return proposalService.getProposals();
    }
}
