package ir.navaco.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * When not found equivalent enum for persist value, throw this exception.
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found equivalent enum for persist value")
public class NotFoundEquivalentEnum extends AbstractException {

    public NotFoundEquivalentEnum(Object... args) {
        super("error.orm.convertColumnToEnum");
        setCode("error.orm.convertColumnToEnum.code");
        setSuggestion("error.orm.convertColumnToEnum.suggestion");
        setHttpStatus(HttpStatus.LOCKED);
        setArgs(args);
    }
}
