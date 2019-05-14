package ir.navaco.core.api;

import ir.navaco.core.exception.NotFoundEquivalentEnum;
import ir.navaco.core.exception.NotSameVersionException;
import ir.navaco.core.exception.ValueObjectValidationException;
import ir.navaco.core.vo.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * This class is exception provider that only return json response
 * and is final class
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@RestControllerAdvice
public final class ExceptionProvider extends AbstractExceptionProvider {

    private final Logger logger = LoggerFactory.getLogger("Exception-Provider");

    /**
     * When throw NotSameVersionException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NotSameVersionException.class)
    public ResponseEntity<ErrorResponse> notSameVersion(NotSameVersionException exception) {
        return ResponseEntity.ok(createResponse(exception));
    }

    /**
     * When throw ValueObjectValidationException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ValueObjectValidationException.class)
    public ResponseEntity<List<ErrorResponse>> valueObjectValidationException(ValueObjectValidationException exception) {
        return ResponseEntity.ok(createResponseFromChildren(exception));
    }

    /**
     * When throw NotFoundEquivalentEnum
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NotFoundEquivalentEnum.class)
    public ResponseEntity<ErrorResponse> notFoundEquivalentEnum(NotFoundEquivalentEnum exception) {
        return ResponseEntity.ok(createResponse(exception));
    }

}
