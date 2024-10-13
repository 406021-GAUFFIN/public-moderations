package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.PlotDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Client class responsible for interacting with the Cadastre service.
 */
@Component
@RequiredArgsConstructor
public final class CadastreClient {

    /**
     * RestTemplate used for making HTTP requests.
     */
    private final RestTemplate restTemplate;

    /**
     * Base URL of the external Cadastre service.
     */
    @Value("${cadastre.url}")
    private String baseUrl;

    /**
     * Retrieves a plot by its ID from the Cadastre service.
     *
     * @param plotId the ID of the plot to retrieve
     * @return the PlotDTO representing the retrieved plot
     */
    public PlotDTO getPlotById(Long plotId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(org.springframework.http.MediaType.ALL)); // Set the Accept header

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<PlotDTO> responseEntity = restTemplate.exchange(
                baseUrl + "/plots/{plotId}",
                HttpMethod.GET,
                requestEntity,
                PlotDTO.class, // Change to PlotDTO
                plotId // Pass the plotId as a path variable
        );

        return responseEntity.getBody(); // Return the body of the response
    }
}
