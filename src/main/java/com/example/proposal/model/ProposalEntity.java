package com.example.proposal.model;

import com.example.proposal.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

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
}
