package com.example.proposal.validator;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.validator.impl.*;

import java.util.List;

public class UpdateValidatorUtil {

    private static final List<UpdateValidator> validators = List.of(
            new CreatedToDeleteValidator(),
            new CreatedToVerifiedValidator(),
            new VerifiedToAcceptedValidator(),
            new AcceptedToPublishedValidator(),
            new VerifiedToRejectedValidator(),
            new AcceptedToRejectedValidator(),
            new ChangeTextValidator());

    private UpdateValidatorUtil() {
        throw new IllegalStateException("Util class");
    }

    public static boolean validate(ProposalEntity previousValue, ProposalDTO nextValue) {
        for (UpdateValidator validator : validators) {
            if (validator.validate(previousValue, nextValue)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTextAndNameEqual(ProposalEntity previousValue, ProposalDTO nextValue) {
        return previousValue.getName().equals(nextValue.getName()) && previousValue.getText().equals(nextValue.getText());
    }
}
