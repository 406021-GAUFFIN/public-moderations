package ar.edu.utn.frc.tup.lc.iv.clients;


import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * Client class responsible for sending fine
 * expenses data to the external expenses service.
 */
@Component
@RequiredArgsConstructor
public final class ExpensesClient {  // Marked final to prevent extension

    /**
     * WebClient used for making HTTP requests.
     */
    private final WebClient webClient;

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
     * @return FineExpenseResponseDTO the response received from the expenses service
     */
    public FineExpenseResponseDTO sendToExpenses(FineExpenseDTO fineExpenseDTO) {
        Mono<FineExpenseResponseDTO> response = webClient.post()
                .uri(baseUrl + "/charges/fines")
                .bodyValue(fineExpenseDTO)
                .retrieve()
                .bodyToMono(FineExpenseResponseDTO.class);
        return response.block();
    }
}

