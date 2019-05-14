package ir.navaco.core.example.domain;

import ir.navaco.core.enums.AbstractEnumConverter;
import ir.navaco.core.example.enums.ExampleType;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class ExampleTypeConverter extends AbstractEnumConverter<Integer, ExampleType> {
}
