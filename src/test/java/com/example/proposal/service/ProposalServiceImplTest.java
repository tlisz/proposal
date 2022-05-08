package com.example.proposal.service;

import com.example.proposal.dto.ProposalDTO;
import com.example.proposal.dto.ProposalPageableRequest;
import com.example.proposal.dto.ProposalPageableResult;
import com.example.proposal.enums.ProposalSortColumn;
import com.example.proposal.enums.State;
import com.example.proposal.exception.ProposalRequestException;
import com.example.proposal.model.ProposalEntity;
import com.example.proposal.repository.ProposalRepository;
import com.example.proposal.service.impl.ProposalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProposalServiceImplTest {

    @InjectMocks
    ProposalServiceImpl proposalService;

    @Mock
    ProposalRepository proposalRepository;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestCaptor;

    @Captor
    ArgumentCaptor<Long> longCaptor;

    @Test
    public void shouldReturnProposals() {
        // given
        ProposalPageableRequest request = new ProposalPageableRequest();
        request.setPageSize(2);
        request.setPage(3);
        request.setSortColumn(ProposalSortColumn.STATE);
        ProposalEntity expectedProposal1 = new ProposalEntity();
        ProposalEntity expectedProposal2 = new ProposalEntity();
        List<ProposalEntity> expectedProposals = List.of(expectedProposal1, expectedProposal2);
        Page<ProposalEntity> queryResult = createQueryResult(expectedProposals);
        Mockito.when(proposalRepository.findAll(any(PageRequest.class))).thenReturn(queryResult);

        // when
        ProposalPageableResult result = proposalService.getProposals(request);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getProposals()).isNotNull();
        assertThat(result.getProposals()).hasSameSizeAs(expectedProposals);
        verify(proposalRepository, Mockito.times(1)).findAll(pageRequestCaptor.capture());
        PageRequest capturedRequest = pageRequestCaptor.getValue();
        assertThat(capturedRequest.getPageNumber()).isEqualTo(request.getPage());
        assertThat(capturedRequest.getPageSize()).isEqualTo(request.getPageSize());
        String expectedColumnName = request.getSortColumn().toString().toLowerCase();
        assertThat(capturedRequest.getSort().getOrderFor(expectedColumnName).getProperty()).isNotNull();
        assertThat(capturedRequest.getSort().getOrderFor(expectedColumnName).getProperty()).isEqualTo(expectedColumnName);
    }

    @Test
    public void shouldReturnProposalsWithDefaultPageSize() {
        // given
        ProposalPageableRequest request = new ProposalPageableRequest();
        request.setPage(3);
        request.setSortColumn(ProposalSortColumn.STATE);
        ProposalEntity expectedProposal1 = new ProposalEntity();
        ProposalEntity expectedProposal2 = new ProposalEntity();
        List<ProposalEntity> expectedProposals = List.of(expectedProposal1, expectedProposal2);
        Page<ProposalEntity> queryResult = createQueryResult(expectedProposals);
        Mockito.when(proposalRepository.findAll(any(PageRequest.class))).thenReturn(queryResult);

        // when
        ProposalPageableResult result = proposalService.getProposals(request);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getProposals()).isNotNull();
        assertThat(result.getProposals()).hasSameSizeAs(expectedProposals);
        verify(proposalRepository, Mockito.times(1)).findAll(pageRequestCaptor.capture());
        PageRequest capturedRequest = pageRequestCaptor.getValue();
        assertThat(capturedRequest.getPageNumber()).isEqualTo(request.getPage());
        assertThat(capturedRequest.getPageSize()).isEqualTo(10);
        String expectedColumnName = request.getSortColumn().toString().toLowerCase();
        assertThat(capturedRequest.getSort().getOrderFor(expectedColumnName).getProperty()).isNotNull();
        assertThat(capturedRequest.getSort().getOrderFor(expectedColumnName).getProperty()).isEqualTo(expectedColumnName);
    }

    @Test
    public void shouldCreateNewProposal() {
        // given
        ProposalDTO newProposal = new ProposalDTO();
        newProposal.setName("name");
        newProposal.setState(State.CREATED);
        newProposal.setText("text");
        long expectedId = 3L;
        ProposalEntity proposal = new ProposalEntity();
        proposal.setId(expectedId);
        proposal.setState(newProposal.getState());
        proposal.setText(newProposal.getText());
        proposal.setReason(newProposal.getReason());
        proposal.setName(newProposal.getName());
        Mockito.when(proposalRepository.save(any())).thenReturn(proposal);

        // when
        ProposalDTO createdProposal = proposalService.createProposal(newProposal);

        // then
        assertThat(createdProposal).isNotNull();
        assertThat(createdProposal.getState()).isEqualTo(newProposal.getState());
        assertThat(createdProposal.getReason()).isEqualTo(newProposal.getReason());
        assertThat(createdProposal.getName()).isEqualTo(newProposal.getName());
        assertThat(createdProposal.getText()).isEqualTo(newProposal.getText());
        assertThat(createdProposal.getId()).isEqualTo(expectedId);
        verify(proposalRepository, times(1)).save(any(ProposalEntity.class));
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNotNull() {
        // given
        ProposalDTO newProposal = new ProposalDTO();
        newProposal.setId(2L);
        newProposal.setName("name");
        newProposal.setState(State.CREATED);
        newProposal.setText("text");

        // when
        assertThatThrownBy(() -> proposalService.createProposal(newProposal))
                .isInstanceOf(ProposalRequestException.class);

        // then
        verify(proposalRepository, never()).save(any(ProposalEntity.class));
    }

    @Test
    public void shouldThrowExceptionWhenReasonIsNotNull() {
        // given
        ProposalDTO newProposal = new ProposalDTO();
        newProposal.setName("name");
        newProposal.setState(State.CREATED);
        newProposal.setText("text");
        newProposal.setReason("reason");

        // when
        assertThatThrownBy(() -> proposalService.createProposal(newProposal))
                .isInstanceOf(ProposalRequestException.class);

        // then
        verify(proposalRepository, never()).save(any(ProposalEntity.class));
    }

    @Test
    public void shouldThrowExceptionWhenStateIsNotCreated() {
        // given
        ProposalDTO newProposal = new ProposalDTO();
        newProposal.setName("name");
        newProposal.setState(State.ACCEPTED);
        newProposal.setText("text");

        // when
        assertThatThrownBy(() -> proposalService.createProposal(newProposal))
                .isInstanceOf(ProposalRequestException.class);

        // then
        verify(proposalRepository, never()).save(any(ProposalEntity.class));
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingFromAcceptedToVerified() {
        // given
        ProposalDTO proposalToUpdate = new ProposalDTO();
        proposalToUpdate.setId(2L);
        proposalToUpdate.setName("name");
        proposalToUpdate.setState(State.VERIFIED);
        proposalToUpdate.setText("text");

        ProposalEntity proposalBeforeUpdate = new ProposalEntity();
        proposalBeforeUpdate.setId(proposalToUpdate.getId());
        proposalBeforeUpdate.setState(State.ACCEPTED);
        proposalBeforeUpdate.setText(proposalToUpdate.getText());
        proposalBeforeUpdate.setReason(proposalToUpdate.getReason());
        proposalBeforeUpdate.setName(proposalToUpdate.getName());

        Mockito.when(proposalRepository.findById(anyLong())).thenReturn(Optional.of(proposalBeforeUpdate));

        // when
        assertThatThrownBy(() -> proposalService.updateProposal(proposalToUpdate))
                .isInstanceOf(ProposalRequestException.class);

        // then
        verify(proposalRepository, never()).save(any(ProposalEntity.class));
        verify(proposalRepository, times(1)).findById(longCaptor.capture());
        assertThat(longCaptor.getValue()).isEqualTo(proposalToUpdate.getId());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNull() {
        // given
        ProposalDTO proposalToUpdate = new ProposalDTO();
        proposalToUpdate.setId(null);
        proposalToUpdate.setName("name");
        proposalToUpdate.setState(State.VERIFIED);
        proposalToUpdate.setText("text");

        // when
        assertThatThrownBy(() -> proposalService.updateProposal(proposalToUpdate))
                .isInstanceOf(ProposalRequestException.class);

        // then
        verify(proposalRepository, never()).save(any(ProposalEntity.class));
        verify(proposalRepository, never()).findById(anyLong());
    }

    @Test
    public void shouldUpdateProposal() {
        // given
        ProposalDTO proposalToUpdate = new ProposalDTO();
        proposalToUpdate.setId(2L);
        proposalToUpdate.setName("name");
        proposalToUpdate.setState(State.ACCEPTED);
        proposalToUpdate.setText("text");

        ProposalEntity proposalBeforeUpdate = new ProposalEntity();
        proposalBeforeUpdate.setId(proposalToUpdate.getId());
        proposalBeforeUpdate.setState(State.VERIFIED);
        proposalBeforeUpdate.setText(proposalToUpdate.getText());
        proposalBeforeUpdate.setReason(proposalToUpdate.getReason());
        proposalBeforeUpdate.setName(proposalToUpdate.getName());

        ProposalEntity savedProposal = new ProposalEntity();
        savedProposal.setId(proposalToUpdate.getId());
        savedProposal.setState(proposalToUpdate.getState());
        savedProposal.setText(proposalToUpdate.getText());
        savedProposal.setReason(proposalToUpdate.getReason());
        savedProposal.setName(proposalToUpdate.getName());

        Mockito.when(proposalRepository.findById(anyLong())).thenReturn(Optional.of(proposalBeforeUpdate));
        Mockito.when(proposalRepository.save(any(ProposalEntity.class))).thenReturn(savedProposal);

        // when
        ProposalDTO updatedProposal = proposalService.updateProposal(proposalToUpdate);

        // then
        assertThat(updatedProposal).isNotNull();
        assertThat(updatedProposal.getState()).isEqualTo(proposalToUpdate.getState());
        assertThat(updatedProposal.getReason()).isEqualTo(proposalToUpdate.getReason());
        assertThat(updatedProposal.getName()).isEqualTo(proposalToUpdate.getName());
        assertThat(updatedProposal.getText()).isEqualTo(proposalToUpdate.getText());
        assertThat(updatedProposal.getId()).isEqualTo(savedProposal.getId());
        verify(proposalRepository, times(1)).save(any(ProposalEntity.class));
        verify(proposalRepository, times(1)).findById(longCaptor.capture());
        assertThat(longCaptor.getValue()).isEqualTo(proposalToUpdate.getId());
    }

    private Page<ProposalEntity> createQueryResult(List<ProposalEntity> proposals) {
        return new Page<ProposalEntity>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super ProposalEntity, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<ProposalEntity> getContent() {
                return proposals;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<ProposalEntity> iterator() {
                return proposals.iterator();
            }
        };
    }
}