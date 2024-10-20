package ar.edu.utn.frc.tup.lc.iv.services;


import ar.edu.utn.frc.tup.lc.iv.clients.ExpensesClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineUpdateStateDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ChargeType;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.impl.FineServiceImpl;
import ar.edu.utn.frc.tup.lc.iv.services.impl.ProofServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FineServiceImplTests {

    @Mock
    private FineJpaRepository fineJpaRepository;
    @Mock
    private ExpensesClient expensesClient;

    @InjectMocks
    private FineServiceImpl fineService;


    @Mock
    @Qualifier("modelMapper")
    private ModelMapper modelMapper;



    @Test
    void updateFineState_FineRejected() {
        Long fineId = 3L;
        FineUpdateStateDTO request = new FineUpdateStateDTO();
        request.setId(fineId);
        request.setFineState(FineState.APPROVED);

        FineEntity existingFine = new FineEntity();
        existingFine.setId(fineId);
        existingFine.setFineState(FineState.REJECTED);

        when(fineJpaRepository.findById(fineId)).thenReturn(Optional.of(existingFine));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fineService.updateFineState(request);
        });

  }



    @Test
    void updateFineState_FineNotFound() {
        Long fineId = 2L;
        FineUpdateStateDTO request = new FineUpdateStateDTO();
        request.setId(fineId);
        request.setFineState(FineState.APPROVED);

        when(fineJpaRepository.findById(fineId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            fineService.updateFineState(request);
        });

        assertEquals("Fine Not Found", exception.getMessage());
    }



    @Test
    void getAllFines_Success() {
        // Given
        Pageable pageable = Pageable.ofSize(10);
        List<FineState> fineStates = List.of(FineState.APPROVED);
        List<Long> sanctionTypes = List.of(1L, 2L);

        FineEntity fineEntity = new FineEntity();
        fineEntity.setId(1L);
        fineEntity.setFineState(FineState.APPROVED);

        List<FineEntity> fineEntities = new ArrayList<>();
        fineEntities.add(fineEntity);

        Page<FineEntity> fineEntityPage = new PageImpl<>(fineEntities, pageable, fineEntities.size());

        when(fineJpaRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(fineEntityPage);
        when(modelMapper.map(fineEntity, FineDTO.class)).thenReturn(new FineDTO());

        // When
        Page<FineDTO> result = fineService.getAllFines(pageable, fineStates, sanctionTypes);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        verify(fineJpaRepository).findAll(any(Specification.class), eq(pageable));

        ArgumentCaptor<Specification<FineEntity>> specificationCaptor = ArgumentCaptor.forClass(Specification.class);
        verify(fineJpaRepository).findAll(specificationCaptor.capture(), eq(pageable));
        // Verify that the filters are applied
        assertNotNull(specificationCaptor.getValue());
    }

    @Test
    void getAllFines_NoFinesFound() {
        // Given
        Pageable pageable = Pageable.ofSize(10);
        List<FineState> fineStates = List.of(FineState.REJECTED);
        List<Long> sanctionTypes = List.of(3L);

        Page<FineEntity> fineEntityPage = new PageImpl<>(new ArrayList<>(), pageable, 0);

        when(fineJpaRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(fineEntityPage);

        // When
        Page<FineDTO> result = fineService.getAllFines(pageable, fineStates, sanctionTypes);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
        verify(fineJpaRepository).findAll(any(Specification.class), eq(pageable));
    }

    /**
     * Tests the scenario when both fine states and sanction types are empty.
     */
    @Test
    void getAllFines_EmptyFilters() {
        // Given
        Pageable pageable = Pageable.ofSize(10);
        List<FineState> fineStates = new ArrayList<>(); // empty list
        List<Long> sanctionTypes = new ArrayList<>(); // empty list

        FineEntity fineEntity = new FineEntity();
        fineEntity.setId(1L);
        fineEntity.setFineState(FineState.APPROVED);

        List<FineEntity> fineEntities = new ArrayList<>();
        fineEntities.add(fineEntity);

        Page<FineEntity> fineEntityPage = new PageImpl<>(fineEntities, pageable, fineEntities.size());

        when(fineJpaRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(fineEntityPage);
        when(modelMapper.map(fineEntity, FineDTO.class)).thenReturn(new FineDTO());

        // When
        Page<FineDTO> result = fineService.getAllFines(pageable, fineStates, sanctionTypes);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        verify(fineJpaRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void getById_Success() {
        // Given
        Long fineId = 1L;
        FineEntity fineEntity = new FineEntity();
        fineEntity.setId(fineId);
        fineEntity.setFineState(FineState.APPROVED); // Assuming there's a state to set

        FineDTO fineDTO = new FineDTO();
        fineDTO.setFineState(FineState.APPROVED); // Set corresponding state in DTO

        // When
        when(fineJpaRepository.findById(fineId)).thenReturn(Optional.of(fineEntity));
        when(modelMapper.map(fineEntity, FineDTO.class)).thenReturn(fineDTO);

        // Then
        FineDTO actualResponse = fineService.getById(fineId);
        assertEquals(fineDTO.getFineState(), actualResponse.getFineState());
    }

    @Test
    void getById_NotFound() {
        // Given
        Long fineId = 2L;

        // When
        when(fineJpaRepository.findById(fineId)).thenReturn(Optional.empty());

        // Then
        assertThrows(EntityNotFoundException.class, () -> fineService.getById(fineId));
    }

    @Test
    void updateFineState_FineApproved_ShouldSendToExpenses() {
        Long fineId = 3L;
        FineUpdateStateDTO request = new FineUpdateStateDTO();
        request.setId(fineId);
        request.setFineState(FineState.APPROVED);

        FineEntity existingFine = new FineEntity();
        existingFine.setId(fineId);
        existingFine.setFineState(FineState.ON_ASSEMBLY);
        SanctionTypeEntity sanctionType = new SanctionTypeEntity();
        sanctionType.setAmount(100.0);
        sanctionType.setName("sanction type");
        sanctionType.setId(1L);
        sanctionType.setChargeType(ChargeType.FIXED);
        existingFine.setSanctionType(sanctionType);





        when(fineJpaRepository.findById(fineId)).thenReturn(Optional.of(existingFine));
        when(fineJpaRepository.save(any(FineEntity.class))).thenReturn(existingFine);

        // Act
        FineDTO result = fineService.updateFineState(request);

        verify(expensesClient).sendToExpenses(any(FineExpenseDTO.class));
    }

}
