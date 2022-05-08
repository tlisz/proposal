package com.example.proposal.model;

import com.example.proposal.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "state_history")
@Data
@EqualsAndHashCode(callSuper = true)
public class StateChangeHistoryEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private State previousState;

    @Enumerated(EnumType.STRING)
    private State nextState;

    private ZonedDateTime date;

    @ManyToOne
    @JoinColumn(name="proposal_id", nullable=false)
    private ProposalEntity proposal;
}
