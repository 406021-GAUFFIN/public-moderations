package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Exception thrown when an error with a webClient fails.
 */
public class ExpensesClientException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new PlotServiceException
     * with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause.
     */
    public ExpensesClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
