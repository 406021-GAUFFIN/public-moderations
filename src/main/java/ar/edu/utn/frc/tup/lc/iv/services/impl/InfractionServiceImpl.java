package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.CreateInfractionDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.InfractionDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.SanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.InfractionState;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.PlotDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.claim.ClaimEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.infraction.InfractionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.proof.ProofEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.claim.ClaimJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.infraction.InfractionJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType.SanctionTypeJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.InfractionService;
import ar.edu.utn.frc.tup.lc.iv.services.SanctionTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service to manage infractions.
 */
@Service
public class InfractionServiceImpl implements InfractionService {

    @Autowired
    private InfractionJpaRepository infractionJpaRepository;

    @Autowired
    private SanctionTypeJpaRepository sanctionTypeJpaRepository;

    @Autowired
    private ClaimJpaRepository claimJpaRepository;

    @Autowired
    private CadastreClient cadastreClient;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Create Infraction and saves it in database.
     * @param  dto fine to create
     * @return created fine dto
     */
    @Override
    public InfractionDTO postInfraction(CreateInfractionDto dto) {
        InfractionEntity infractionEntity = new InfractionEntity();


        //check the plot is valid
        //todo fijarse si esto es necesario o confiamos en el front y lo sacamos
        PlotDTO plotDTO = cadastreClient.getPlotById(dto.getPlotId());
        if (plotDTO == null) {
            throw new EntityNotFoundException("Plot not found");
        }

        //check the sanction type is valid
        SanctionTypeEntity sanctionTypeEntity = sanctionTypeJpaRepository.findById(dto.getSanctionTypeId())
                .orElseThrow(()-> new EntityNotFoundException("Sanction Type not found"));



        //construct the rest of the entity
        infractionEntity.setPlotId(dto.getPlotId());
        infractionEntity.setDescription(dto.getDescription());
        infractionEntity.setInfractionState(InfractionState.CREATED);
        infractionEntity.setSanctionTypeEntity(sanctionTypeEntity); //if it breaks it might be this since it isn't what it was done before
        infractionEntity.setClaims(new ArrayList<>());


        //add all the claims into the infraction
        //todo: agregar una validacion para que no agrege reclamos que ya estan en otra infraccion O manejarlo del front que no mande nunca un reclamo ya infraccionado,de todas maneras esto no esta en la user story actual
        for (int i = 0; i < dto.getClaimsId().size(); i++) {
            Optional<ClaimEntity> claimEntityOptional = claimJpaRepository.findById(dto.getClaimsId().get(i));
            if (claimEntityOptional.isPresent()){
                //this adds the claims into the infraction
                ClaimEntity claimEntity = claimEntityOptional.get();
                infractionEntity.getClaims().add(claimEntity);
                //this adds the proofs contained in the claims to the proof list the infraction
                for (int j = 0; j < claimEntity.getProofs().size(); j++) {
                    infractionEntity.getProofs().add(claimEntity.getProofs().get(j));
                }
            }
        }
        //saving the entity and adding it's id
        infractionEntity = infractionJpaRepository.save(infractionEntity);

        InfractionDTO result = modelMapper.map(infractionEntity, InfractionDTO.class);

        result.setSanctionType(modelMapper.map(sanctionTypeEntity, SanctionTypeDTO.class));

        return result;
    }
}
