package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;

import ar.edu.utn.frc.tup.lc.iv.dtos.FineUpdateStateDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.dtos.CreateFineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Service to manage infractions.
 */
@Service
public interface FineService {
    /**
     * TGet all fines, paginated and filtered.
     * @param pageable Pageable object with page size and current page
     * @param fineState list of fine state to filter
     * @param sanctionTypes list of sanction types to filter
     *
     * @return all fines paginated and filtered
     */
    Page<FineDTO> getAllFines(Pageable pageable, List<FineState> fineState, List<Long> sanctionTypes);

    /**
     * Get one fine by id.
     * @param  id of the fine to get
     * @return fine dto
     */
    FineDTO getById(Long id);

    /**
     * Create fine.
     * @param  createFineDTO fine to create
     * @return created fine dto
     */
    FineDTO postFine(CreateFineDTO createFineDTO);
    /**
     * Updates the state of a fine.
     *
     * @param request the DTO containing the fine ID
     *                and the new state
     * @return the updated FineDTO
     */
    FineDTO updateFineState(FineUpdateStateDTO request);




}
