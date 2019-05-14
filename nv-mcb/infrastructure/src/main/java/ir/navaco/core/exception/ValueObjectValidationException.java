package ir.navaco.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * When value object is invalid throw this exception
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "input data is invalid.")
public final class ValueObjectValidationException extends AbstractException {

    public ValueObjectValidationException(String message, Object... args) {
        super(message);
        setArgs(args);
        setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ValueObjectValidationException() {
        super("");
    }

}
