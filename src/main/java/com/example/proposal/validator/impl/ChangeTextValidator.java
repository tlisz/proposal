package com.example.proposal.validator.impl;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.enums.State;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.validator.UpdateValidator;

public class ChangeTextValidator implements UpdateValidator {
    @Override
    public boolean validate(ProposalEntity previousValue, ProposalDTO nextValue) {
        return !previousValue.getText().equals(nextValue.getText()) &&
                previousValue.getName().equals(nextValue.getName()) &&
                isStageChangeValid(previousValue, nextValue);
    }

    private boolean isStageChangeValid(ProposalEntity previousValue, ProposalDTO nextValue) {
        State prevState = previousValue.getState();
        State nextState = nextValue.getState();
        return (prevState.equals(State.CREATED) && nextState.equals(State.CREATED)) ||
                (prevState.equals(State.CREATED) && nextState.equals(State.VERIFIED)) ||
                (prevState.equals(State.VERIFIED) && nextState.equals(State.VERIFIED));
    }
}
