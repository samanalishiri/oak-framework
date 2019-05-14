package ir.navaco.core.service;

import java.util.List;
import java.util.Optional;

public interface SearchService<ID, V> {
    Optional<List<V>> findAll();
}
