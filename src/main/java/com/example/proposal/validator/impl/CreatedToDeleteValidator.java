package com.example.proposal.validator.impl;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.enums.State;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.validator.UpdateValidator;
import com.example.proposal.validator.UpdateValidatorUtil;

import java.util.Objects;

public class CreatedToDeleteValidator implements UpdateValidator {
    @Override
    public boolean validate(ProposalEntity previousValue, ProposalDTO nextValue) {
        return UpdateValidatorUtil.isTextAndNameEqual(previousValue, nextValue) &&
                previousValue.getState().equals(State.CREATED) && nextValue.getState().equals(State.DELETED) &&
                Objects.nonNull(nextValue.getReason());
    }
}