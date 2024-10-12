package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.SanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.services.SanctionTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SanctionTypeControllerTest {

    @InjectMocks
    private SanctionTypeController sanctionTypeController;

    @Mock
    private SanctionTypeService sanctionTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSanctionTypesPaginated() {
        List<SanctionTypeDTO> sanctionTypeList = new ArrayList<>();
        sanctionTypeList.add(new SanctionTypeDTO());
        Page<SanctionTypeDTO> page = new PageImpl<>(sanctionTypeList);

        when(sanctionTypeService.getAllSanctionTypes(any(), any())).thenReturn(page);

        ResponseEntity<Page<SanctionTypeDTO>> response = sanctionTypeController.getSanctionTypesPaginated(0, 10, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }

    @Test
    public void testGetSanctionTypes() {
        List<SanctionTypeDTO> sanctionTypeList = new ArrayList<>();
        sanctionTypeList.add(new SanctionTypeDTO());

        when(sanctionTypeService.getAllSanctionTypes(any())).thenReturn(sanctionTypeList);

        ResponseEntity<List<SanctionTypeDTO>> response = sanctionTypeController.getSanctionTypes(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sanctionTypeList, response.getBody());
    }

    @Test
    public void testGetSanctionTypeById() {
        SanctionTypeDTO sanctionTypeDTO = new SanctionTypeDTO();

        when(sanctionTypeService.getById(1L)).thenReturn(sanctionTypeDTO);

        ResponseEntity<SanctionTypeDTO> response = sanctionTypeController.getSanctionTypeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sanctionTypeDTO, response.getBody());
    }
}
