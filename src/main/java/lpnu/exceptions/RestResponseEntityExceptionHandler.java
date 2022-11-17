package lpnu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = InternalException.class)
    public ResponseEntity<Object> handleServiceException(final InternalException ex, final WebRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode())).body(new IntervalExceptionDTO(ex.getCode(),
                ex.getMessage(), ex.getDetails()));
    }
}
