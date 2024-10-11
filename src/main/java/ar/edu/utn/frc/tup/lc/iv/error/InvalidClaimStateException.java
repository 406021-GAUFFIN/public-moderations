package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Exception thrown when an invalid claim state is encountered.
 */
public class InvalidClaimStateException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidClaimStateException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidClaimStateException(String message) {
        super(message);
    }
}
