package ir.navaco.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.navaco.core.exception.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class ErrorResponse {

    private String message;

    private String code;

    private String suggestion;

    private HttpStatus httpStatus;

    public ErrorResponse() {
    }

    @JsonIgnore
    public static ErrorResponse of(AbstractException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = e.getLocalMessage();
        errorResponse.code = e.getLocalCode();
        errorResponse.suggestion = e.getLocalSuggestion();
        errorResponse.httpStatus = e.getHttpStatus();

        return errorResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
