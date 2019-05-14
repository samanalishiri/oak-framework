package ir.navaco.core.example.api;

import ir.navaco.core.api.AbstractFilter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This class use as an object that hold operand and operator
 * for make where-class in repository layer.
 *
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
@Component(ExampleFilter.BEAN_NAME)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExampleFilter extends AbstractFilter {
    public static final String BEAN_NAME = "exampleFilter";
}
