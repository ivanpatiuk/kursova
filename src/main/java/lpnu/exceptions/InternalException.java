package lpnu.exceptions;

public class InternalException extends RuntimeException{
    private int code;
    private String message;
    private String details;

    public InternalException() { }

    public InternalException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
    public InternalException(final int code, final String message, final String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
    public InternalException(final int code, final String message, final Throwable cause, final String details) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
