package com.saman.oak.core.converter;

import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import com.saman.oak.core.utils.CollectionUtils;
import com.saman.oak.core.utils.GenericUtils;
import com.saman.oak.core.validation.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.saman.oak.core.utils.CollectionUtils.EMPTY_LIST;
import static com.saman.oak.core.utils.CollectionUtils.createArray;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class Converter<E extends BaseEntity, M extends BaseModel> {

    public static final int LIGHT_DEEP = -1;
    public static final int EXIT = 0;

    private final Class<M> modelType = (Class<M>) GenericUtils.extract(this.getClass(), 1);
    private final Class<E> entityType = (Class<E>) GenericUtils.extract(this.getClass(), 0);

    public void before(E e, int deep, String... relations) {
        ObjectUtils.requireNonNull(e, new NullPointerException(e.getClass().getSimpleName() + " is null"));
    }

    public abstract void internal(E input, M output, int deep, String... relations);

    public void after(E e, M m, int deep, String... relations) {
    }

    public M convert(E e, int deep, String... relations) {
        M model = newModel();

        before(e, deep, relations);
        internal(e, model, deep, relations);
        after(e, model, deep, relations);

        return model;
    }

    public M convert(E e, String... relations) {
        return convert(e, LIGHT_DEEP, relations);
    }

    public List<M> convertEntities(List<E> entities, int deep, String... relations) {
        return CollectionUtils.isEmpty(entities)
                ? EMPTY_LIST
                : entities.stream().map(e -> convert(e, deep, relations)).collect(Collectors.toList());
    }

    public M[] convert(E[] entities, int deep, String... relations) {
        return CollectionUtils.isEmpty(entities)
                ? createArray(0, modelType)
                : Arrays.stream(entities).map(e -> convert(e, deep, relations)).toArray(size -> createArray(size, modelType));
    }

    public void before(M m, int deep, String... relations) {
        ObjectUtils.requireNonNull(m, new NullPointerException(m.getClass().getSimpleName() + " is null"));
    }

    public abstract void internal(M input, E output, int deep, String... relations);

    public void after(M m, E e, int deep, String... relations) {
    }

    public E convert(M m, int deep, String... relations) {
        return convert(m, newEntity(), deep, relations);
    }

    public E convert(M m, E e, int deep, String... relations) {

        before(m, deep, relations);
        internal(m, e, deep, relations);
        after(m, e, deep, relations);

        return e;
    }

    public E convert(M m, String... relations) {
        return convert(m, LIGHT_DEEP, relations);
    }

    public List<E> convertModels(List<M> models, int deep, String... relations) {
        return CollectionUtils.isEmpty(models)
                ? EMPTY_LIST
                : models.stream().map(m -> convert(m, deep, relations)).collect(Collectors.toList());

    }

    public E[] convert(M[] models, int deep, String... relations) {
        return CollectionUtils.isEmpty(models)
                ? createArray(0, entityType)
                : Arrays.stream(models).map(e -> convert(e, deep, relations)).toArray(size -> createArray(size, entityType));
    }

    public abstract E newEntity();

    public abstract M newModel();
}
