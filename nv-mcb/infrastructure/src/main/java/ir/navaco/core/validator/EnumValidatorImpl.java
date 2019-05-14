package ir.navaco.core.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private final Logger logger = LoggerFactory.getLogger(EnumValidatorImpl.class);

    private List<String> values;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return values.contains(value.toUpperCase());
    }

    @Override
    public void initialize(EnumValidator annotation) {
        values = Stream.of(annotation.clazz().getEnumConstants())
                .map(e -> e.toString().toUpperCase())
                .collect(Collectors.toList());
    }

}