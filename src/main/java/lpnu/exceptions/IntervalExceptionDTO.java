package lpnu.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Клас виняткової ситуації
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntervalExceptionDTO {
    private int code;
    private String message;
    private String details;

    public IntervalExceptionDTO(final InternalException ex) {
        this.code = ex.getCode();
        this.message = ex.getMessage();
        this.details = ex.getDetails();
    }
    public IntervalExceptionDTO(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
//************************************************

