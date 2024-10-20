package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.CreateInfractionDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.InfractionDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.SanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.PlotDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.claim.ClaimEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.claim.ClaimJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.infraction.InfractionJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType.SanctionTypeJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.impl.InfractionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InfractionServiceImplTest {

    @Mock
    private InfractionJpaRepository infractionJpaRepository;

    @Mock
    private SanctionTypeJpaRepository sanctionTypeJpaRepository;

    @Mock
    private ClaimJpaRepository claimJpaRepository;

    @Mock
    private CadastreClient cadastreClient;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private InfractionServiceImpl infractionService;

    private CreateInfractionDto createInfractionDto;
    private InfractionEntity infractionEntity;
    private SanctionTypeEntity sanctionTypeEntity;
    private ClaimEntity claimEntity;
    private PlotDTO plotDTO;

    @BeforeEach
    public void setUp(){
        // Setup de datos de prueba
        createInfractionDto = new CreateInfractionDto(1L, "Infraction Description", 1L, List.of(1L, 2L));

        sanctionTypeEntity = new SanctionTypeEntity();
        sanctionTypeEntity.setId(1L);
        sanctionTypeEntity.setName("Sanction Type");

        plotDTO = new PlotDTO();
        plotDTO.setId(1L);
        plotDTO.setPlotId(1L);

        claimEntity = new ClaimEntity();
        claimEntity.setId(1L);
        claimEntity.setProofs(new ArrayList<>()); // Puedes agregar pruebas aquí si es necesario

        infractionEntity = new InfractionEntity();
        infractionEntity.setPlotId(1L);
        infractionEntity.setDescription("Infraction Description");
        infractionEntity.setSanctionTypeEntity(sanctionTypeEntity);
        infractionEntity.setClaims(new ArrayList<>());
    }

    @Test
    public void postInfractionSuccTest(){
        when(sanctionTypeJpaRepository.findById(createInfractionDto.getSanctionTypeId()))
                .thenReturn(Optional.of(sanctionTypeEntity));

        when(cadastreClient.getPlotById(createInfractionDto.getPlotId()))
                .thenReturn(plotDTO);

        when(claimJpaRepository.findById(1L)).thenReturn(Optional.of(claimEntity));
        when(infractionJpaRepository.save(any(InfractionEntity.class))).thenReturn(infractionEntity);

        // Simulamos el mapeo de entidades a DTOs
        when(modelMapper.map(any(InfractionEntity.class),eq(InfractionDTO.class)))
                .thenReturn(new InfractionDTO());
        when(modelMapper.map(any(SanctionTypeEntity.class), eq(SanctionTypeDTO.class)))
                .thenReturn(new SanctionTypeDTO());

        InfractionDTO result = infractionService.postInfraction(createInfractionDto);

        assertNotNull(result);
        assertEquals(createInfractionDto.getPlotId(),infractionEntity.getPlotId());
        assertEquals(createInfractionDto.getDescription(), infractionEntity.getDescription());

        verify(sanctionTypeJpaRepository).findById(createInfractionDto.getSanctionTypeId());
        verify(cadastreClient).getPlotById(createInfractionDto.getPlotId());
        verify(infractionJpaRepository).save(any(InfractionEntity.class));
    }

    @Test
    public void postInfractionPlotNotFoundTest(){
        // Mockeamos para que el cliente de catastro no encuentre el plot
        when(cadastreClient.getPlotById(createInfractionDto.getPlotId()))
                .thenReturn(null);

        // Ejecutamos el método y verificamos que lanza una excepción
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            infractionService.postInfraction(createInfractionDto);
        });

        assertEquals("Plot not found", thrown.getMessage());

        // Verificamos que no se haya llamado al repositorio de infracciones
        verify(infractionJpaRepository, never()).save(any(InfractionEntity.class));
    }



}
