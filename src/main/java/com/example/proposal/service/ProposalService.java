package com.example.proposal.service;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;

public interface ProposalService {
    ProposalPageableResult getProposals(ProposalPageableRequest request);
    ProposalDTO createProposal(ProposalDTO newProposal);
    ProposalDTO updateProposal(ProposalDTO proposalToUpdate);
}
