package com.example.proposal.mapper;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.model.ProposalEntity;

public class ProposalMapper {

    private ProposalMapper() {
        throw new IllegalStateException("Util class");
    }

    public static ProposalDTO toDto(ProposalEntity proposalToMap) {
        if (proposalToMap == null) {
            return null;
        }

        ProposalDTO mappedProposal = new ProposalDTO();
        mappedProposal.setId(proposalToMap.getId());
        mappedProposal.setReason(proposalToMap.getReason());
        mappedProposal.setState(proposalToMap.getState());
        mappedProposal.setText(proposalToMap.getText());
        mappedProposal.setName(proposalToMap.getName());
        return mappedProposal;
    }

    public static ProposalEntity toEntity(ProposalDTO proposalToMap) {
        if (proposalToMap == null) {
            return null;
        }

        ProposalEntity mappedProposal = new ProposalEntity();
        mappedProposal.setId(proposalToMap.getId());
        mappedProposal.setReason(proposalToMap.getReason());
        mappedProposal.setState(proposalToMap.getState());
        mappedProposal.setText(proposalToMap.getText());
        mappedProposal.setName(proposalToMap.getName());
        return mappedProposal;
    }
}
