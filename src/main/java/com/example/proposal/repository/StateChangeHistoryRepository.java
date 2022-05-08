package com.example.proposal.repository;

import com.example.proposal.model.StateChangeHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateChangeHistoryRepository extends JpaRepository<StateChangeHistoryEntity, Long> {

    List<StateChangeHistoryEntity> findByProposalId(Long proposalId);
}
