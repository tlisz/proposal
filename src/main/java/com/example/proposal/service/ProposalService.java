package com.example.proposal.service;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;

import java.util.List;

public interface ProposalService {
    ProposalPageableResult getProposals(ProposalPageableRequest request);
}
