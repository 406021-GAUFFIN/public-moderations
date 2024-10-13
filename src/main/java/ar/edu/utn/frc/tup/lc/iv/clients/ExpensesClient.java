package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client class responsible for sending fine
 * expenses data to the external expenses service.
 */
@Component
@RequiredArgsConstructor
public final class ExpensesClient {

    /**
     * RestTemplate used for making HTTP requests.
     */
    private final RestTemplate restTemplate;

    /**
     * Base URL of the external expenses service.
     */
    @Value("${expenses.url}")
    private String baseUrl;

    /**
     * Sends fine expense data to the expenses service
     * and retrieves the response.
     *
     * @param fineExpenseDTO the data transfer object
     *                       containing fine expense information
     */
    public void sendToExpenses(FineExpenseDTO fineExpenseDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        HttpEntity<FineExpenseDTO> requestEntity = new HttpEntity<>(fineExpenseDTO, headers);
        ResponseEntity<FineExpenseResponseDTO> responseEntity = restTemplate.exchange(
                baseUrl + "/charges/fines",
                HttpMethod.POST,
                requestEntity,
                FineExpenseResponseDTO.class
        );

        responseEntity.getBody();
    }
}

