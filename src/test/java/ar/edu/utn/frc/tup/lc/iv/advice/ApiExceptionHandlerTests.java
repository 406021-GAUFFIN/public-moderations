package ar.edu.utn.frc.tup.lc.iv.advice;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.error.InvalidClaimStateException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApiExceptionHandlerTests {

    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;
    @Mock
    private BindingResult bindingResult;
    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";


    /**
     * Tests {@link ApiExceptionHandler#handleAllExceptions(Exception)} for handling generic exceptions.
     *
     * <p>Verifies that the method returns a {@code 500 Internal Server Error} status, the correct error details,
     * and a non-null timestamp.
     *
     * @see ApiExceptionHandler#handleAllExceptions(Exception)
     */
    @Test
    public void testHandleAllExceptions() {
        ApiExceptionHandler handler = new ApiExceptionHandler();
        Exception ex = new Exception("Generic error");

        ResponseEntity<ErrorApi> response = handler.handleAllExceptions(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals("Internal Server Error", response.getBody().getError());
        assertEquals("Generic error", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());

    }

    @Test
    public void testHandleMethodArgumentNotValid() {

        FieldError fieldError = new FieldError("object", "field", "Field is invalid");
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError));

        MethodParameter parameter = mock(MethodParameter.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleMethodArgumentNotValid(exception);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals("Field is invalid", response.getBody().getError());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleMethodArgumentNotValid_NoErrors() {

        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList());

        MethodParameter parameter = mock(MethodParameter.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleMethodArgumentNotValid(exception);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals("Validation error occurred", response.getBody().getError());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleNotFoundException() {

        String errorMessage = "Claim not found";
        EntityNotFoundException ex = new EntityNotFoundException(errorMessage);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleNotFoundExceptiob(ex);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), response.getBody().getError());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());


        String timestamp = response.getBody().getTimestamp();
        assertDoesNotThrow(() -> LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void testHandleInvalidClaimStateException() {

        String errorMessage = "The claim state is not valid for adding proof.";
        InvalidClaimStateException ex = new InvalidClaimStateException(errorMessage);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleInvalidClaimStateException(ex);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getBody().getError());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());


        String timestamp = response.getBody().getTimestamp();
        assertDoesNotThrow(() -> LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(DATE_FORMATTER)));
    }
}
