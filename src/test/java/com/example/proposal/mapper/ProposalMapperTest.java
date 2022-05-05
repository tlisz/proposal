package com.example.proposal.mapper;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.enums.State;
import com.example.proposal.model.ProposalEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProposalMapperTest {

    @Test
    public void shouldMapToDto() {
        // given
        ProposalEntity proposalToMap = new ProposalEntity();
        String expectedName = "name";
        String expectedReason = "reason";
        proposalToMap.setName(expectedName);
        State expectedState = State.ACCEPTED;
        String expectedText = "text";
        long expectedId = 5L;
        proposalToMap.setReason(expectedReason);
        proposalToMap.setState(expectedState);
        proposalToMap.setText(expectedText);
        proposalToMap.setId(expectedId);

        // when
        ProposalDTO mappedProposal = ProposalMapper.toDto(proposalToMap);

        // then
        assertThat(mappedProposal).isNotNull();
        assertThat(mappedProposal.getId()).isEqualTo(expectedId);
        assertThat(mappedProposal.getName()).isEqualTo(expectedName);
        assertThat(mappedProposal.getReason()).isEqualTo(expectedReason);
        assertThat(mappedProposal.getText()).isEqualTo(expectedText);
        assertThat(mappedProposal.getState()).isEqualTo(expectedState);
    }

    @Test
    public void shouldMapToEntity() {
        // given
        ProposalDTO proposalToMap = new ProposalDTO();
        String expectedName = "name";
        String expectedReason = "reason";
        proposalToMap.setName(expectedName);
        State expectedState = State.ACCEPTED;
        String expectedText = "text";
        long expectedId = 5L;
        proposalToMap.setReason(expectedReason);
        proposalToMap.setState(expectedState);
        proposalToMap.setText(expectedText);
        proposalToMap.setId(expectedId);

        // when
        ProposalEntity mappedProposal = ProposalMapper.toEntity(proposalToMap);

        // then
        assertThat(mappedProposal).isNotNull();
        assertThat(mappedProposal.getId()).isEqualTo(expectedId);
        assertThat(mappedProposal.getName()).isEqualTo(expectedName);
        assertThat(mappedProposal.getReason()).isEqualTo(expectedReason);
        assertThat(mappedProposal.getText()).isEqualTo(expectedText);
        assertThat(mappedProposal.getState()).isEqualTo(expectedState);
    }

    @Test
    public void shouldReturnNull() {
        // given

        // then
        ProposalDTO mappedDTO = ProposalMapper.toDto(null);
        ProposalEntity mappedEntity = ProposalMapper.toEntity(null);

        // then
        assertThat(mappedDTO).isNull();
        assertThat(mappedEntity).isNull();
    }
}