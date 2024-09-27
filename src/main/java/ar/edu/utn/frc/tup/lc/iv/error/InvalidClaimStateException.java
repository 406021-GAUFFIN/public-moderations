package ar.edu.utn.frc.tup.lc.iv.error;


import java.io.Serial;

public class InvalidClaimStateException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidClaimStateException(String message) {
        super(message);
    }
}
