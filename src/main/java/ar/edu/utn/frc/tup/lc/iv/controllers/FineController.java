package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.models.Fine;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ping controller class to health check.
 */

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class FineController {

    @Autowired
    private
    FineService fineService;

    /**
     * Get all fines
     *
     * @return paginated fines
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
    public Page<Fine> getFines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return fineService.getAllFines(pageable);
    }

    /**
     * Get fine by id
     *
     * @return fine
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
    public Fine getFineById(
            @PathVariable Long id
    ) {

        return fineService.getById(id);
    }
}
