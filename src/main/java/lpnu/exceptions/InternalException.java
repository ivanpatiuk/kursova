package lpnu.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Клас виняткової ситуації
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalException extends RuntimeException{
    private int code;
    private String message;
    private String details;

    public InternalException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
//************************************************
