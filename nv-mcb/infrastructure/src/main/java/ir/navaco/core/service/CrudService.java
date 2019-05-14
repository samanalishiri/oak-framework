package ir.navaco.core.service;

import java.util.Optional;

public interface CrudService<ID, V> {
    Optional<ID> save(V v);

    Optional<V> findById(ID id);

    void update(V v);

    void delete(ID id);
}
