package ir.navaco.core.enums;

import ir.navaco.core.exception.NotFoundEquivalentEnum;
import ir.navaco.core.util.GenericUtils;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.Objects;

/**
 * This use for convert enums to database columns and vice versa.
 *
 * @param <T> database column type
 * @param <E> implemented enum
 * @author Saman Alishiri, samanalishiri@gmail.com
 */

public abstract class AbstractEnumConverter<T, E extends Enum<E> & Convertible<T>> implements AttributeConverter<E, T> {

    private Class<E> enumClassType;

    public AbstractEnumConverter() {
        enumClassType = (Class<E>) GenericUtils.extract(this.getClass(), 1);
    }

    @Override
    public T convertToDatabaseColumn(E attribute) {
        return attribute.getValue();
    }

    @Override
    public E convertToEntityAttribute(T dbData) {

        if (Objects.isNull(dbData)) {
            return null;
        }

        return EnumSet.allOf(enumClassType).stream()
                .filter(e -> Objects.equals(e.getValue(), dbData))
                .findFirst()
                .orElseThrow(NotFoundEquivalentEnum::new);
    }
}
