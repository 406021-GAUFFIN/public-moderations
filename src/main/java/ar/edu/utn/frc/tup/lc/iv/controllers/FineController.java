package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.FineDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.FineUpdateStateDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.FineState;
import ar.edu.utn.frc.tup.lc.iv.dtos.CreateFineDTO;
import ar.edu.utn.frc.tup.lc.iv.services.FineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;



import java.util.List;

/**
 * Fine controller class to manage fines.
 */

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class FineController {

    /**
     * Service to manage fines.
     */
    @Autowired
    private
    FineService fineService;

    /**
     * Get all fines.
     * @param page number of current page
     * @param size size of page
     * @param fineState array of states to filter
     * @param sanctionTypes array of sanction types to filter
     * @return paginated fines.
     */

    @Operation(
            summary = "Get all fines",
            description = "Get all fines")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("pageable/fine")
    public ResponseEntity<Page<FineDTO>> getFines(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(required = false) List<FineState> fineState,
                                                  @RequestParam(required = false) List<Long> sanctionTypes) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(fineService.getAllFines(pageable, fineState, sanctionTypes), HttpStatus.OK);
    }

    /**
     * Get fine by id.
     *
     * @param id id of the fine to get
     * @return fine.
     */
    @Operation(
            summary = "Get one fine",
            description = "Get fine by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("fine/{id}")
    public ResponseEntity<FineDTO> getFineById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(fineService.getById(id), HttpStatus.OK);
    }


    /**
     * Post  fine.
     * @param fineDTO to create.
     * @return created fine.
     */
    @Operation(
            summary = "Post  fine",
            description = "Post  fine")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = FineDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Plot or Sanction Type Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PostMapping("fine")
    public ResponseEntity<FineDTO> postFine(
            @RequestBody CreateFineDTO fineDTO
            ) {
        return new ResponseEntity<>(fineService.postFine(fineDTO), HttpStatus.OK);
    }



    /**
     * Update the state of a fine.
     * This endpoint allows updating the state of an existing fine.
     *
     * @param fineDTO the DTO containing the fine id and the new state to update
     * @return ResponseEntity containing the updated FineDTO
     * and an HTTP status code of 200 (OK)
     */
    @Operation(
            summary = "Update fine state",
            description = "Update the state of a fine by providing the fine ID and the new state"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Fine state updated successfully",
                    content = @Content(
                            schema = @Schema(implementation = FineDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request, invalid input or state transition",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PutMapping("/fine/state")
    public ResponseEntity<FineDTO> updateFineState(@RequestBody FineUpdateStateDTO fineDTO) {
        return ResponseEntity.ok(fineService.updateFineState(fineDTO));
    }

}
