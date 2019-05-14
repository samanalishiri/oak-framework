package ir.navaco.core.enums;

/**
 * When a enum wants to convert to database column and vice versa, use this interface.
 *
 * @param <T>
 */
public interface Convertible<T> {
    /**
     * value accessor
     *
     * @return
     */
    T getValue();
}
