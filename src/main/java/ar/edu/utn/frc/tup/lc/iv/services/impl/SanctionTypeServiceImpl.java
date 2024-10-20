package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.CreateSanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.SanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.UpdateSanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.SanctionTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType.SanctionTypeJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.services.SanctionTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ar.edu.utn.frc.tup.lc.iv.repositories.jpa.sanctionType.SanctionTypeSpecification.partialName;

/**
 * Service to manage infractions.
 */
@Service
@RequiredArgsConstructor
public class SanctionTypeServiceImpl implements SanctionTypeService {

    /**
     * Fine repository.
     */
    private final SanctionTypeJpaRepository sanctionTypeJpaRepository;

    /**
     * Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Get sanction types, paginated and filtered.
     * @param pageable Pageable object with page size and current page
     * @param sanctionTypeName name of sanction type for partial search
     *
     * @return all sanction types, paginated and filtered.
     */
    @Override
    public Page<SanctionTypeDTO> getAllSanctionTypes(Pageable pageable, String sanctionTypeName) {

        Specification<SanctionTypeEntity> filters = Specification.where(sanctionTypeName == null ? null : partialName(sanctionTypeName));

        Page<SanctionTypeEntity> sanctionTyoeEntityPage = sanctionTypeJpaRepository.findAll(filters, pageable);

        return  sanctionTyoeEntityPage.map(fineEntity -> (modelMapper.map(fineEntity, SanctionTypeDTO.class)));
    }
    /**
     * Get sanction typesand filtered.
     * @param partialName name of sanction type for partial search
     *
     * @return all sanction types filtered.
     */
    @Override
    public List<SanctionTypeDTO> getAllSanctionTypes(String partialName) {
        Specification<SanctionTypeEntity> filters = Specification.where(partialName == null ? null : partialName(partialName));

        List<SanctionTypeEntity> sanctionTypeEntityList = sanctionTypeJpaRepository.findAll(filters);

        return sanctionTypeEntityList.stream()
                .map(entity -> modelMapper.map(entity, SanctionTypeDTO.class))
                .collect(Collectors.toList());
    }


    /**
     * Get one sanction type by id.
     * @param id of the sanction type to get
     * @return canction type dto
     */

    @Override
    public SanctionTypeDTO getById(Long id) {
        Optional<SanctionTypeEntity> sanctionTypeEntity = sanctionTypeJpaRepository.findById(id);

        if (sanctionTypeEntity.isEmpty()) {
            throw new EntityNotFoundException("Sanction Type Not Found");
        }

        return modelMapper.map(sanctionTypeEntity.get(), SanctionTypeDTO.class);
    }

    /**
     * Create sanction type.
     * @param createSanctionTypeDTO of the sanction type to create
     * @return creation type dto
     */
    @Override
    public SanctionTypeDTO postSanctionType(CreateSanctionTypeDTO createSanctionTypeDTO) {

        SanctionTypeEntity sanctionTypeEntity = new SanctionTypeEntity();

        sanctionTypeEntity.setCreatedBy(createSanctionTypeDTO.getUserId());
        sanctionTypeEntity.setLastUpdatedBy(createSanctionTypeDTO.getUserId());
        sanctionTypeEntity.setCreatedDate(LocalDateTime.now());
        sanctionTypeEntity.setLastUpdatedAt(LocalDateTime.now());
        sanctionTypeEntity.setName(createSanctionTypeDTO.getName());
        sanctionTypeEntity.setDescription(createSanctionTypeDTO.getDescription());
        sanctionTypeEntity.setAmount(createSanctionTypeDTO.getAmount());
        sanctionTypeEntity.setChargeType(createSanctionTypeDTO.getChargeType());

        return modelMapper.map(sanctionTypeJpaRepository.save(sanctionTypeEntity), SanctionTypeDTO.class);
    }

    /**
     * Create sanction type.
     * @param id of the sanction type to edit
     * @param  updateSanctionTypeDTO of the sanction type to update
     * @return sanction type dto
     */
    @Override
    public SanctionTypeDTO update(Long id, UpdateSanctionTypeDTO updateSanctionTypeDTO) {
        SanctionTypeEntity sanctionTypeEntity = sanctionTypeJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sanction Type not found"));

        sanctionTypeEntity.setLastUpdatedBy(updateSanctionTypeDTO.getUserId());
        sanctionTypeEntity.setLastUpdatedAt(LocalDateTime.now());
        sanctionTypeEntity.setChargeType(updateSanctionTypeDTO.getChargeType());
        sanctionTypeEntity.setAmount(updateSanctionTypeDTO.getAmount());
        sanctionTypeEntity.setDescription(updateSanctionTypeDTO.getDescription());

        return modelMapper.map(sanctionTypeJpaRepository.save(sanctionTypeEntity), SanctionTypeDTO.class);


    }

}
