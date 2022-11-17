package lpnu.exceptions;

public class IntervalExceptionDTO {
    private int code;
    private String message;
    private String details;

    public IntervalExceptionDTO() { }

    public IntervalExceptionDTO(final InternalException ex) {
        this.code = ex.getCode();
        this.message = ex.getMessage();
        this.details = ex.getDetails();
    }
    public IntervalExceptionDTO(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public IntervalExceptionDTO(final int code, final String message, final String details) {
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
