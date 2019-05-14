package ir.navaco.core.util;

import ir.navaco.core.domain.AbstractEntity;
import ir.navaco.core.vo.AbstractVo;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public abstract class Transformer<I extends Serializable, E extends AbstractEntity<I>, V extends AbstractVo<I>> {

    public static final int FIRST_DEEP = -1;
    public static final int EXIT = 0;

    protected Class<? extends AbstractVo> getModelClass() {
        return (Class<V>) GenericUtils.extract(this.getClass(), 2);
    }

    protected Class<? extends AbstractEntity> getEntityClass() {
        return (Class<E>) GenericUtils.extract(this.getClass(), 1);
    }

    protected E newEntity() {
        try {
            return (E) getEntityClass().newInstance();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    protected V newModel() {
        try {
            return (V) getModelClass().newInstance();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    protected abstract void transformEntityToModel(E input, V output, int deep, String... fields);

    protected abstract void transformModelToEntity(V input, E output, int deep, String... fields);

    public V transform(E e, int deep, String... fields) {
        V v = newModel();
        transformEntityToModel(e, v, deep, fields);
        additionalToModel(e, v, deep, fields);
        getAudit(e, v);
        return v;
    }

    public E transform(V v, int deep, String... fields) {
        return transform(v, newEntity(), deep, fields);
    }

    public E transform(V v, E e, int deep, String... fields) {
        transformModelToEntity(v, e, deep, fields);
        additionalToEntity(e, v, deep, fields);
        setAudit(e, v);
        return e;
    }

    protected void additionalToModel(E e, V v, int deep, String... fields) {
    }

    protected void additionalToEntity(E e, V v, int deep, String... fields) {
    }

    private void getAudit(E e, V v) {
        v.setVersion(e.getVersion());
    }

    private void setAudit(E e, V v) {
        e.setVersion(v.getVersion());
    }

    public V transform(E e, String... fields) {
        return transform(e, FIRST_DEEP, fields);
    }

    public E transform(V v, String... fields) {
        return transform(v, FIRST_DEEP, fields);
    }

    public E transform(V v, E e, String... fields) {
        return transform(v, e, FIRST_DEEP, fields);
    }

    public V[] transform(E[] entities, int deep, String... fields) {
        return CollectionUtils.isEmpty(entities)
                ? (V[]) Array.newInstance(getModelClass(), 0)
                : Arrays.stream(entities).map(e -> transform(e, deep, fields)).toArray(size -> (V[]) Array.newInstance(getModelClass(), size));
    }

    public E[] transform(V[] models, int deep, String... fields) {
        return CollectionUtils.isEmpty(models)
                ? (E[]) Array.newInstance(getModelClass(), 0)
                : Arrays.stream(models).map(e -> transform(e, deep, fields)).toArray(size -> (E[]) Array.newInstance(getModelClass(), size));
    }

    public List<V> transformEntitiesToModels(Supplier<List<E>> entities, int deep, String... fields) {
        return CollectionUtils.isEmpty(entities.get())
                ? CollectionUtils.EMPTY_LIST
                : entities.get().stream().map(e -> transform(e, deep, fields)).collect(Collectors.toList());
    }

    public List<E> transformModelsToEntities(Supplier<List<V>> models, int deep, String... fields) {
        return CollectionUtils.isEmpty(models.get())
                ? CollectionUtils.EMPTY_LIST
                : models.get().stream().map(v -> transform(v, deep, fields)).collect(Collectors.toList());
    }

}
