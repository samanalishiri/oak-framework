package ir.navaco.core.util;

import ir.navaco.core.exception.AbstractException;
import ir.navaco.core.exception.ValueObjectValidationException;
import ir.navaco.core.helper.MessageHelper;
import org.springframework.validation.Errors;

import java.util.Objects;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class ExceptionUtils {

    public static ValueObjectValidationException transform(Errors errors) {

        ValueObjectValidationException parentException = new ValueObjectValidationException();

        errors.getFieldErrors().stream()
                .map(error -> new ValueObjectValidationException(error.getDefaultMessage(), error.getField()))
                .forEach(parentException::addChild);

        throw parentException;
    }

    public static void setLocalMessage(AbstractException e, MessageHelper messageHelper) {
        e.setLocalMessage(messageHelper.getMessage(e.getMessage(), e.getArgs()));

        if (Objects.nonNull(e.getCode())) {
            e.setLocalCode(messageHelper.getMessage(e.getCode()));
        }

        if (Objects.nonNull(e.getSuggestion())) {
            e.setLocalSuggestion(messageHelper.getMessage(e.getSuggestion()));
        }
    }
}
