package com.example.proposal.model;

import com.example.proposal.enums.State;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "proposals")
@Data
public class ProposalEntity extends BaseEntity {
    private String text;
    private String reason;
    private State state;
}
