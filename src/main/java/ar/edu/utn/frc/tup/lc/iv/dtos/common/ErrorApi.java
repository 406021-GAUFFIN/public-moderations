package ar.edu.utn.frc.tup.lc.iv.dtos.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Error API DTO class.
 */


@Builder
@Getter
@Setter

public class ErrorApi {

    /**
     * Timestamp when the error occurred.
     */
    private String timestamp;

    /**
     * Error code number.
     */
    private Integer status;

    /**
     * Error Code name.
     */
    private String error;

    /**
     * Error Code description.
     */
    private String message;

    /**
     * Error Code error list for dto validation.
     */
    private List<String> errors;

    /**
     * Constructor with all fields.
     * @param errorTimestamp the timestamp of the error
     * @param errorStatus the HTTP status code
     * @param errorCode the error name
     * @param errorMessage the error description
     * @param validationErrors list of validation error messages
     */
    public ErrorApi(String errorTimestamp, Integer errorStatus, String errorCode, String errorMessage, List<String> validationErrors) {
        this.timestamp = errorTimestamp;
        this.status = errorStatus;
        this.error = errorCode;
        this.message = errorMessage;
        this.errors = validationErrors;
    }

    /**
     * Constructor without the errors field.
     *
     * @param errorTimestamp the timestamp of the error
     * @param errorStatus the HTTP status code
     * @param errorCode the error name
     * @param errorMessage the error description
     */
    public ErrorApi(String errorTimestamp, Integer errorStatus, String errorCode, String errorMessage) {
        this.timestamp = errorTimestamp;
        this.status = errorStatus;
        this.error = errorCode;
        this.message = errorMessage;
    }
}
