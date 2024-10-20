package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.CreateSanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.SanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.UpdateSanctionTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage infractions.
 */
@Service
public interface SanctionTypeService {
    /**
     * Get all fines, paginated and filtered.
     * @param pageable Pageable object with page size and current page
     * @param sanctionTypeName partial sanction type name to filter
     *
     * @return all sanction type paginated and filtered
     */
    Page<SanctionTypeDTO> getAllSanctionTypes(Pageable pageable, String sanctionTypeName);

    /**
     * Get all fines filtered.
     * @param partialName partial sanction type name to filter
     * @return all sanction type  filtered
     */
    List<SanctionTypeDTO> getAllSanctionTypes(String partialName);

    /**
     * Get one fine by id.
     * @param  id of the fine to get
     * @return fine dto
     */
    SanctionTypeDTO getById(Long id);

    /**
     * Create sanction type.
     * @param  createSanctionTypeDTO of the sanction type to create
     * @return sanction type dto
     */
    SanctionTypeDTO postSanctionType(CreateSanctionTypeDTO createSanctionTypeDTO);

    /**
     * Create sanction type.
     * @param id of the sanction type to edit
     * @param  updateSanctionTypeDTO of the sanction type to update
     * @return sanction type dto
     */
    SanctionTypeDTO update(Long id, UpdateSanctionTypeDTO updateSanctionTypeDTO);
}
