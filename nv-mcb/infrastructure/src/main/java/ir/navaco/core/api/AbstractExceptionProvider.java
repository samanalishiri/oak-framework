package ir.navaco.core.api;

import ir.navaco.core.exception.AbstractException;
import ir.navaco.core.helper.MessageHelper;
import ir.navaco.core.vo.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static ir.navaco.core.util.ExceptionUtils.setLocalMessage;

/**
 * All of centralize exception providers should use this abstract class
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class AbstractExceptionProvider {

    @Autowired
    private MessageHelper messageHelper;

    /**
     * Create error response list from children of AbstractExceptions
     *
     * @param exception
     * @return
     */
    public List<ErrorResponse> createResponseFromChildren(AbstractException exception) {
        return exception.getChildren().stream()
                .peek(ex -> setLocalMessage(ex, messageHelper))
                .map(ErrorResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * Create error response from AbstractException.
     *
     * @param exception
     * @return
     */
    public ErrorResponse createResponse(AbstractException exception) {
        setLocalMessage(exception, messageHelper);
        return ErrorResponse.of(exception);
    }

}
