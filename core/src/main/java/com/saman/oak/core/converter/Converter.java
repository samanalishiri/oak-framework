package com.saman.oak.core.converter;

import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.model.BaseModel;
import com.saman.oak.core.utils.CollectionUtils;
import com.saman.oak.core.utils.GenericUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.saman.oak.core.utils.CollectionUtils.EMPTY_LIST;

/**
 * Created by saman on 10/22/2017.
 */
public abstract class Converter<E extends BaseEntity, M extends BaseModel> {

    public static final int LIGHT_DEEP = -1;
    public static final int EXIT = 0;

    private final Class<? extends BaseModel> modelType = (Class<? extends BaseModel>) GenericUtils.extract(this.getClass(), 0);

    public abstract M convert(E e, int deep, String... relations);

    public M convert(E e, String... relations) {
        return convert(e, LIGHT_DEEP, relations);
    }

    public List<M> convertEntities(List<E> entities, int deep, String... relations) {
        return CollectionUtils.isEmpty(entities) ? EMPTY_LIST
                : entities.stream().map(e -> convert(e, deep, relations)).collect(Collectors.toList());
    }

    public M[] convert(E[] entities, int deep, String... relations) {
        return CollectionUtils.isEmpty(entities) ? (M[]) Array.newInstance(modelType, 0)
                : Arrays.stream(entities).map(e -> convert(e, deep, relations)).toArray(size -> (M[]) Array.newInstance(modelType, size));
    }

    public abstract E convert(M m, int deep, String... relations);

    public E convert(M m, String... relations) {
        return convert(m, LIGHT_DEEP, relations);
    }

    public List<E> convertModels(List<M> models, int deep, String... relations) {
        return CollectionUtils.isEmpty(models) ? EMPTY_LIST
                : models.stream().map(m -> convert(m, deep, relations)).collect(Collectors.toList());

    }

    public E[] convert(M[] models, int deep, String... relations) {
        return CollectionUtils.isEmpty(models) ? (E[]) Array.newInstance(modelType, 0)
                : Arrays.stream(models).map(e -> convert(e, deep, relations)).toArray(size -> (E[]) Array.newInstance(modelType, size));
    }
}
