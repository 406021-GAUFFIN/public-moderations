package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ChargeType;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.SanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.UpdateSanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType.SanctionTypeJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SanctionTypeServiceImplTest {

    @InjectMocks
    private SanctionTypeServiceImpl sanctionTypeService;

    @Mock
    private SanctionTypeJpaRepository sanctionTypeJpaRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSanctionTypesPaginated() {
        Pageable pageable = Pageable.ofSize(10);
        SanctionTypeEntity entity = new SanctionTypeEntity();
        SanctionTypeDTO dto = new SanctionTypeDTO();

        when(sanctionTypeJpaRepository.findAll((Specification<SanctionTypeEntity>) any(), (Pageable) any())).thenReturn(new PageImpl<>(Collections.singletonList(entity)));
        when(modelMapper.map(entity, SanctionTypeDTO.class)).thenReturn(dto);

        Page<SanctionTypeDTO> result = sanctionTypeService.getAllSanctionTypes(pageable, null);

        assertEquals(1, result.getTotalElements());
        assertEquals(dto, result.getContent().get(0));
    }

    @Test
    public void testGetAllSanctionTypes() {
        SanctionTypeEntity entity = new SanctionTypeEntity();
        SanctionTypeDTO dto = new SanctionTypeDTO();

        when(sanctionTypeJpaRepository.findAll((Specification<SanctionTypeEntity>) any())).thenReturn(Collections.singletonList(entity));
        when(modelMapper.map(entity, SanctionTypeDTO.class)).thenReturn(dto);

        List<SanctionTypeDTO> result = sanctionTypeService.getAllSanctionTypes(null);

        assertEquals(1, ((List<?>) result).size());
        assertEquals(dto, result.get(0));
    }

    @Test
    public void testGetByIdFound() {
        Long id = 1L;
        SanctionTypeEntity entity = new SanctionTypeEntity();
        SanctionTypeDTO dto = new SanctionTypeDTO();

        when(sanctionTypeJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, SanctionTypeDTO.class)).thenReturn(dto);

        SanctionTypeDTO result = sanctionTypeService.getById(id);

        assertEquals(dto, result);
    }

    @Test
    public void testGetByIdNotFound() {
        Long id = 1L;

        when(sanctionTypeJpaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sanctionTypeService.getById(id));
    }

    @Test
    public void testUpdateSanctionTypeFound() {
        Long id = 1L;
        UpdateSanctionTypeDTO updateDTO = new UpdateSanctionTypeDTO();
        updateDTO.setUserId(1L);
        updateDTO.setChargeType(ChargeType.FIXED);
        updateDTO.setAmount(150.0);
        updateDTO.setDescription("Updated Description");

        SanctionTypeEntity entity = new SanctionTypeEntity();
        entity.setId(id);
        SanctionTypeDTO dto = new SanctionTypeDTO();

        when(sanctionTypeJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(modelMapper.map(any(), any())).thenReturn(dto);
        when(sanctionTypeJpaRepository.save(any(SanctionTypeEntity.class))).thenReturn(entity);

        SanctionTypeDTO result = sanctionTypeService.update(id, updateDTO);
        assertEquals(dto, result);
    }

    @Test
    public void testUpdateSanctionTypeNotFound() {
        Long id = 1L;
        UpdateSanctionTypeDTO updateDTO = new UpdateSanctionTypeDTO();
        updateDTO.setUserId(1L);

        when(sanctionTypeJpaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sanctionTypeService.update(id, updateDTO));
    }

}
