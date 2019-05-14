package ir.navaco.core.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * All of exceptions should extend AbstractException.
 * All of exceptions are runtime exception and will handle with centralized exception handling mechanism.
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class AbstractException extends RuntimeException {

    private String code;

    private String suggestion;

    private Object[] args;

    private HttpStatus httpStatus;

    private String localMessage;

    private String localCode;

    private String localSuggestion;

    private List<AbstractException> children = new ArrayList<>();

    public AbstractException(String message) {
        super(message);
        init();
    }

    public void init() {
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

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getLocalMessage() {
        return localMessage;
    }

    public void setLocalMessage(String message) {
        this.localMessage = message;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getLocalSuggestion() {
        return localSuggestion;
    }

    public void setLocalSuggestion(String localSuggestion) {
        this.localSuggestion = localSuggestion;
    }

    public void addChild(ValueObjectValidationException e) {
        children.add(e);
    }

    public List<AbstractException> getChildren() {
        return children;
    }

    public void setChildren(List<AbstractException> children) {
        this.children = children;
    }
}
