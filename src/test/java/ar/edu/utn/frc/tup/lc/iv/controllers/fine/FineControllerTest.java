package ar.edu.utn.frc.tup.lc.iv.controllers.fine;

import ar.edu.utn.frc.tup.lc.iv.controllers.FineController;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.CreateFineDTO;
import ar.edu.utn.frc.tup.lc.iv.services.FineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FineControllerTest {

    @InjectMocks
    private FineController fineController;

    @Mock
    private FineService fineService;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFines() {

        List<FineDTO> fines = Arrays.asList(new FineDTO(), new FineDTO());
        Page<FineDTO> finePage = new PageImpl<>(fines);
        when(fineService.getAllFines(any(), any(), any())).thenReturn(finePage);


        ResponseEntity<Page<FineDTO>> response = fineController.getFines(0, 10, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(finePage, response.getBody());
        verify(fineService, times(1)).getAllFines(any(), any(), any());
    }

    @Test
    public void testGetFineById() {
        Long id = 1L;
        FineDTO fineDTO = new FineDTO();
        when(fineService.getById(id)).thenReturn(fineDTO);

        ResponseEntity<FineDTO> response = fineController.getFineById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fineDTO, response.getBody());
        verify(fineService, times(1)).getById(id);
    }

    @Test
    public void testPostFine() {
        CreateFineDTO createFineDTO = new CreateFineDTO();
        FineDTO fineDTO = new FineDTO();
        when(fineService.postFine(createFineDTO)).thenReturn(fineDTO);


        ResponseEntity<FineDTO> response = fineController.postFine(createFineDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fineDTO, response.getBody());
        verify(fineService, times(1)).postFine(createFineDTO);
    }
}
