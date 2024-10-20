package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.CreateInfractionDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.InfractionDTO;
import ar.edu.utn.frc.tup.lc.iv.services.InfractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class InfractionControllerTest {
    @InjectMocks
    private InfractionController infractionController;

    @Mock
    private InfractionService infractionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostInfraction() {
        CreateInfractionDto createInfractionDto = new CreateInfractionDto();
        InfractionDTO infractionDTO = new InfractionDTO();

        when(infractionService.postInfraction(createInfractionDto)).thenReturn(infractionDTO);

        ResponseEntity<InfractionDTO> result = infractionController.postInfraction(createInfractionDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(infractionDTO, result.getBody());
    }

}
