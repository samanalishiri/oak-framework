package ir.navaco.core.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public class CollectionUtils {

    public static final int DEFAULT_SIZE = 10;
    public static final int MIN_SIZE = 0;

    public static final List EMPTY_LIST = createSearchableList(Object.class, MIN_SIZE);

    public static <E> List<E> createSearchableList(Class<E> c, int length) {

        if (length < MIN_SIZE) {
            throw new NullPointerException("array.exception.null");
        }

        return new ArrayList<E>(length);
    }

    public static <E> List<E> createEditableList(Class<E> c, int length) {
        if (length < MIN_SIZE) {
            throw new NullPointerException("array.exception.null");
        }

        return new LinkedList<E>();
    }

    public static boolean hasElement(List list) {
        return Objects.nonNull(list) && !list.isEmpty();
    }

    public static boolean hasElement(Object... objects) {
        return Objects.nonNull(objects) && objects.length >= MIN_SIZE;
    }

    public static boolean isEmpty(List list) {
        return Objects.isNull(list) || list.isEmpty();
    }

    public static boolean isEmpty(Object... array) {
        return Objects.isNull(array) || array.length > -1;
    }

    public static String[] subFields(List<String> list, String field) {
        return list.stream()
                .filter(s -> s.startsWith(field + "."))
                .map(s -> s.replace(field + ".", ""))
                .toArray(String[]::new);
    }
}
