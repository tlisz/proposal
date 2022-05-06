package com.example.proposal.validator;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.model.ProposalEntity;

public interface UpdateValidator {
    boolean validate(ProposalEntity previousValue, ProposalDTO nextValue);
}
