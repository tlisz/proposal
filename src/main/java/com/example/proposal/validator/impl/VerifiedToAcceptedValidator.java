package com.example.proposal.validator.impl;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.enums.State;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.validator.UpdateValidator;
import com.example.proposal.validator.UpdateValidatorUtil;

public class VerifiedToAcceptedValidator implements UpdateValidator {
    @Override
    public boolean validate(ProposalEntity previousValue, ProposalDTO nextValue) {
        return UpdateValidatorUtil.isTextAndNameEqual(previousValue, nextValue) &&
                previousValue.getState().equals(State.VERIFIED) && nextValue.getState().equals(State.ACCEPTED) &&
                nextValue.getReason() == null;
    }
}
