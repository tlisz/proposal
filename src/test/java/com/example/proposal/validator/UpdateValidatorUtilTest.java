package com.example.proposal.validator;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.enums.State;
import com.example.proposal.model.ProposalEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UpdateValidatorUtilTest {

    @Test
    void shouldReturnTrueOnCreatedToDeleted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.CREATED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text");
        nextValue.setReason("reason");
        nextValue.setState(State.DELETED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueOnCreatedToVerified() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.CREATED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text");
        nextValue.setReason(null);
        nextValue.setState(State.VERIFIED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueOnVerifiedToAccepted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text");
        nextValue.setReason(null);
        nextValue.setState(State.ACCEPTED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueOnVerifiedToRejected() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text");
        nextValue.setReason("reason");
        nextValue.setState(State.REJECTED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueOnAcceptedToRejected() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.ACCEPTED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text");
        nextValue.setReason("reason");
        nextValue.setState(State.REJECTED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueOnTextChangeOnCreated() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.CREATED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.CREATED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueOnTextChangeOnVerified() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.VERIFIED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnTrueOnTextChangeOnCreatedToVerified() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.CREATED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.VERIFIED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseOnTextChangeOnCreatedToDeleted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.CREATED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.DELETED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnTextChangeOnVerifiedToRejected() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.REJECTED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnTextChangeOnVerifiedToAccepted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.ACCEPTED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnVerifiedToPublish() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason(null);
        nextValue.setState(State.PUBLISHED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnVerifiedToDeleted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.DELETED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnVerifiedToCreated() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.VERIFIED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason(null);
        nextValue.setState(State.CREATED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnAcceptedToVerified() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.ACCEPTED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason(null);
        nextValue.setState(State.VERIFIED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnAcceptedToCreated() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.ACCEPTED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason(null);
        nextValue.setState(State.CREATED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnAcceptedToDeleted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.ACCEPTED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.DELETED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnPublishedToDeleted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.PUBLISHED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.DELETED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnPublishedToRejected() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.PUBLISHED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.DELETED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnPublishedToAccepted() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.PUBLISHED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason(null);
        nextValue.setState(State.ACCEPTED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnFalseOnAcceptedToPublishedWithReason() {
        // given
        ProposalEntity prevValue = new ProposalEntity();
        prevValue.setName("name");
        prevValue.setText("text");
        prevValue.setReason(null);
        prevValue.setState(State.ACCEPTED);
        prevValue.setId(1L);
        ProposalDTO nextValue = new ProposalDTO();
        nextValue.setName("name");
        nextValue.setText("text2");
        nextValue.setReason("reason");
        nextValue.setState(State.PUBLISHED);
        nextValue.setId(1L);

        // when
        boolean result = UpdateValidatorUtil.validate(prevValue, nextValue);

        // then
        assertThat(result).isFalse();
    }
}