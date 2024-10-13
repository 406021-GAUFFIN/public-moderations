package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.clients.ExpensesClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.FineExpenseResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ExpensesClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExpensesClient expensesClient;

    @Value("${expenses.url}")
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendToExpenses() {
        // Arrange
        FineExpenseDTO fineExpenseDTO = new FineExpenseDTO();
        // Configurar fineExpenseDTO según sea necesario, por ejemplo:
        // fineExpenseDTO.setAmount(100);
        // fineExpenseDTO.setFineId(1L);

        FineExpenseResponseDTO responseDTO = new FineExpenseResponseDTO();
        // Configura la respuesta DTO según sea necesario

        ResponseEntity<FineExpenseResponseDTO> responseEntity = ResponseEntity.ok(responseDTO);

        // Mock de la respuesta del RestTemplate
        when(restTemplate.exchange(
                eq(baseUrl + "/charges/fines"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(FineExpenseResponseDTO.class)
        )).thenReturn(responseEntity);

        // Act
        expensesClient.sendToExpenses(fineExpenseDTO);

        // Assert
        verify(restTemplate).exchange(
                eq(baseUrl + "/charges/fines"),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(FineExpenseResponseDTO.class)
        );
    }
}
