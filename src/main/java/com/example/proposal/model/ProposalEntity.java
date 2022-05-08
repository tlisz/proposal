package com.example.proposal.model;

import com.example.proposal.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "proposals")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProposalEntity extends BaseEntity {
    private String name;
    private String text;

    @Enumerated(EnumType.STRING)
    private State state;
    private String reason;

    @OneToMany(mappedBy="proposal")
    private Set<StateChangeHistoryEntity> history;
}
