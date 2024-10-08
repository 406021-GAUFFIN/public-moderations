package ar.edu.utn.frc.tup.lc.iv.scheduled;


import ar.edu.utn.frc.tup.lc.iv.clients.ExpensesClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.scheduled.FineExpenseCron;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FineExpenseCronTest {
    @MockBean
    private ExpensesClient expensesClient;

    @MockBean
    private FineJpaRepository fineJpaRepository;

    @Autowired
    private FineExpenseCron fineExpenseCron;

    @Test
    void imputeFines_Success() {
        // Given
        SanctionTypeEntity sanctionType = new SanctionTypeEntity();
        sanctionType.setPrice(100.0);
        sanctionType.setValidityPeriod(30); // 30 days

        FineEntity fineEntity = new FineEntity();
        fineEntity.setId(1L);
        fineEntity.setFineState(FineState.APPROVED);
        fineEntity.setLastUpdatedAt(LocalDateTime.now().minusDays(31)); // more than 30 days old
        fineEntity.setSanctionType(sanctionType);

        when(fineJpaRepository.findAllByFineState(FineState.APPROVED)).thenReturn(Collections.singletonList(fineEntity));

        // When
        fineExpenseCron.imputeFines();

        // Then
        verify(expensesClient).sendToExpenses(any(FineExpenseDTO.class));

        ArgumentCaptor<FineExpenseDTO> fineExpenseDTOCaptor = ArgumentCaptor.forClass(FineExpenseDTO.class);
        verify(expensesClient).sendToExpenses(fineExpenseDTOCaptor.capture());
        FineExpenseDTO capturedDTO = fineExpenseDTOCaptor.getValue();

        assertEquals(fineEntity.getId(), capturedDTO.getFineId());
        assertEquals(sanctionType.getPrice(), capturedDTO.getAmount());

        assertEquals(FineState.IMPUTED_ON_EXPENSE, fineEntity.getFineState());
        verify(fineJpaRepository).save(fineEntity); // Verify that save was called
    }

    /**
     * Tests the scenario where a fine is still challengeable.
     * Verifies that the fine is not processed and its state remains unchanged.
     */
    @Test
    void imputeFines_FineStillChallengeable() {
        // Given
        SanctionTypeEntity sanctionType = new SanctionTypeEntity();
        sanctionType.setPrice(100.0);
        sanctionType.setValidityPeriod(30); // 30 days

        FineEntity fineEntity = new FineEntity();
        fineEntity.setId(2L);
        fineEntity.setFineState(FineState.APPROVED);
        fineEntity.setLastUpdatedAt(LocalDateTime.now().minusDays(10)); // less than 30 days old
        fineEntity.setSanctionType(sanctionType);

        when(fineJpaRepository.findAllByFineState(FineState.APPROVED)).thenReturn(Collections.singletonList(fineEntity));

        // When
        fineExpenseCron.imputeFines();

        // Then
        verify(expensesClient, never()).sendToExpenses(any()); // Ensure sendToExpenses was never called
        assertEquals(FineState.APPROVED, fineEntity.getFineState()); // Ensure fine state remains unchanged
        verify(fineJpaRepository, never()).save(any()); // Ensure save was never called
    }

    /**
     * Tests the scenario where an error occurs while processing a fine.
     * Verifies that the error is logged but processing continues for other fines.
     */
    @Test
    void imputeFines_ErrorProcessingFine() {
        // Given
        SanctionTypeEntity sanctionType = new SanctionTypeEntity();
        sanctionType.setPrice(100.0);
        sanctionType.setValidityPeriod(30); // 30 days

        FineEntity fineEntity = new FineEntity();
        fineEntity.setId(3L);
        fineEntity.setFineState(FineState.APPROVED);
        fineEntity.setLastUpdatedAt(LocalDateTime.now().minusDays(31)); // more than 30 days old
        fineEntity.setSanctionType(sanctionType);

        when(fineJpaRepository.findAllByFineState(FineState.APPROVED)).thenReturn(Collections.singletonList(fineEntity));
        doThrow(new RuntimeException("Mocked error")).when(expensesClient).sendToExpenses(any());

        // When
        fineExpenseCron.imputeFines();

        // Then
        verify(expensesClient).sendToExpenses(any(FineExpenseDTO.class));

    }
}
