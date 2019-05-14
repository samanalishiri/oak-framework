package ir.navaco.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * When version of input data is different with persisted data throw this exception.
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@ResponseStatus(code = HttpStatus.LOCKED, reason = "version of input data and persisted data are not the same as each other.")
public final class NotSameVersionException extends AbstractException {

    public NotSameVersionException(Object... args) {
        super("error.business.audit.version");
        setCode("error.business.audit.version.code");
        setSuggestion("error.business.audit.version.suggestion");
        setHttpStatus(HttpStatus.LOCKED);
        setArgs(args);
    }
}
