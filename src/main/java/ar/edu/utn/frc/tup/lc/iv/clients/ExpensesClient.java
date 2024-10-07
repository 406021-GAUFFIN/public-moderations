package ar.edu.utn.frc.tup.lc.iv.clients;


import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class ExpensesClient {

    private final WebClient webClient;

    @Value("${expenses.url}")
    private String baseUrl;


    public FineExpenseResponseDTO sendToExpenses(FineExpenseDTO fineExpenseDTO) {

        Mono<FineExpenseResponseDTO> response = webClient.post()
                .uri(baseUrl + "/charges/fines")
                .bodyValue(fineExpenseDTO)
                .retrieve()
                .bodyToMono(FineExpenseResponseDTO.class);
    return response.block();
    }
}
