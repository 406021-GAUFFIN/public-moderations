package ar.edu.utn.frc.tup.lc.iv.controllers;


import ar.edu.utn.frc.tup.lc.iv.controllers.api_response.ApiResponseConstants;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.CreateSanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.SanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.sanctionType.UpdateSanctionTypeDTO;
import ar.edu.utn.frc.tup.lc.iv.services.SanctionTypeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

/**
 * Fine controller class to manage fines.
 */

@CrossOrigin(origins = "*")
@RequestMapping("/sanction-type")
@RestController
public class SanctionTypeController {

    /**
     * Service to manage fines.
     */
    @Autowired
    private
    SanctionTypeService sanctionTypeService;

    /**
     * Get all sanction types.
     * @param page number of current page
     * @param size size of page
     * @param partialName partial name to filter
     * @return paginated sanctionTypes.
     */

    @Operation(
            summary = "Get all sanction types",
            description = "Find all paginated sanction types o")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = Page.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("/pageable")
    public ResponseEntity<Page<SanctionTypeDTO>> getSanctionTypesPaginated(@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size,
                                                                           @RequestParam(required = false) String partialName) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(sanctionTypeService.getAllSanctionTypes(pageable, partialName), HttpStatus.OK);
    }


    /**
     * Get all sanction types.
     * @param partialName partial name to filter
     * @return  sanctionTypes.
     */

    @Operation(
            summary = "Get filtered sanction types",
            description = "Get filtered sanction types")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = List.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<SanctionTypeDTO>> getSanctionTypes(
                                                          @RequestParam(required = false) String partialName) {

        return new ResponseEntity<>(sanctionTypeService.getAllSanctionTypes(partialName), HttpStatus.OK);
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
                            schema = @Schema(implementation = SanctionTypeDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Sanction type not found",
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
    @GetMapping("/{id}")
    public ResponseEntity<SanctionTypeDTO> getSanctionTypeById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(sanctionTypeService.getById(id), HttpStatus.OK);
    }


    /**
     * Post  sanctionType.
     * @param createSanctionTypeDTO to create.
     * @return created sanction type.
     */
    @Operation(
            summary = "Post sanction Type",
            description = "Post sanction Type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = SanctionTypeDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PostMapping
    public ResponseEntity<SanctionTypeDTO> createSanctionType(
            @RequestBody CreateSanctionTypeDTO createSanctionTypeDTO
    ) {
        return new ResponseEntity<>(sanctionTypeService.postSanctionType(createSanctionTypeDTO), HttpStatus.OK);
    }

    /**
     * Create sanction type.
     * @param id of the sanction type to edit
     * @param  updateSanctionTypeDTO of the sanction type to update
     * @return sanction type dto
     */
    @Operation(
            summary = "Update fine state",
            description = "Update the state of a fine by providing the fine ID and the new state"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = SanctionTypeDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Sanction type not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<SanctionTypeDTO> updateSanctionType(
            @PathVariable Long id,
            @RequestBody UpdateSanctionTypeDTO updateSanctionTypeDTO) {
        return ResponseEntity.ok(sanctionTypeService.update(id, updateSanctionTypeDTO));
    }

}
