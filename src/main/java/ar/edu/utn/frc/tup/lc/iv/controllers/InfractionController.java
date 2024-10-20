package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.controllers.api_response.ApiResponseConstants;

import ar.edu.utn.frc.tup.lc.iv.dtos.CreateInfractionDto;

import ar.edu.utn.frc.tup.lc.iv.dtos.InfractionDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.services.InfractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Infraction controller class to manage fines.
 */
@RestController
@RequestMapping("/infractions")
public class InfractionController {

    /**
     * Infraction service to use the methods inside it to return the correct dto.
     */
    @Autowired
    private InfractionService service;

    /**
     * Post  Infraction.
     * @param dto to create.
     * @return created fine.
     */
    @Operation(
            summary = "Post  infraction",
            description = "Post  infraction")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = InfractionDTO.class)
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
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PostMapping("")
    public ResponseEntity<InfractionDTO> postInfraction(@RequestBody CreateInfractionDto dto) {
        return ResponseEntity.ok(service.postInfraction(dto));
    }
}
