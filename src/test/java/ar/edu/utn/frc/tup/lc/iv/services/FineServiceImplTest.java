package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.CreateFineDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.fine.FineEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.FineJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.fine.SanctionTypeJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.impl.FineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class FineServiceImplTest {

    @InjectMocks
    private FineServiceImpl fineService;

    @Mock
    private FineJpaRepository fineJpaRepository;

    @Mock
    private SanctionTypeJpaRepository sanctionTypeJpaRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFines() {
        List<FineEntity> fineEntities = Arrays.asList(new FineEntity(), new FineEntity());
        Page<FineEntity> fineEntityPage = new PageImpl<>(fineEntities);
        when(fineJpaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(fineEntityPage);

        FineDTO fineDTO = new FineDTO();
        when(modelMapper.map(any(FineEntity.class), eq(FineDTO.class))).thenReturn(fineDTO);

        Page<FineDTO> result = fineService.getAllFines(Pageable.ofSize(10), List.of(), List.of());

        Assertions.assertEquals(2, result.getTotalElements());
        verify(fineJpaRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    public void testGetById_Success() {
        Long id = 1L;
        FineEntity fineEntity = new FineEntity();
        when(fineJpaRepository.findById(id)).thenReturn(Optional.of(fineEntity));
        FineDTO fineDTO = new FineDTO();
        when(modelMapper.map(fineEntity, FineDTO.class)).thenReturn(fineDTO);

        FineDTO result = fineService.getById(id);

        Assertions.assertNotNull(result);
        verify(fineJpaRepository, times(1)).findById(id);
    }

    @Test
    public void testGetById_NotFound() {
        Long id = 1L;
        when(fineJpaRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> fineService.getById(id));
    }

    @Test
    public void testPostFine() {
        CreateFineDTO createFineDTO = new CreateFineDTO();
        createFineDTO.setSanctionTypeId(1L);
        createFineDTO.setPlotId(1L);

        SanctionTypeEntity sanctionTypeEntity = new SanctionTypeEntity();
        when(sanctionTypeJpaRepository.findById(createFineDTO.getSanctionTypeId())).thenReturn(Optional.of(sanctionTypeEntity));

        FineEntity fineEntity = new FineEntity();
        when(fineJpaRepository.save(any(FineEntity.class))).thenReturn(fineEntity);
        when(modelMapper.map(fineEntity, FineDTO.class)).thenReturn(new FineDTO());

        FineDTO result = fineService.postFine(createFineDTO);

        Assertions.assertNotNull(result);
        verify(sanctionTypeJpaRepository, times(1)).findById(createFineDTO.getSanctionTypeId());
        verify(fineJpaRepository, times(2)).save(any(FineEntity.class));
    }

    @Test
    public void testPostFine_SanctionTypeNotFound() {
        CreateFineDTO createFineDTO = new CreateFineDTO();
        createFineDTO.setSanctionTypeId(1L);

        when(sanctionTypeJpaRepository.findById(createFineDTO.getSanctionTypeId())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> fineService.postFine(createFineDTO));
    }
}

