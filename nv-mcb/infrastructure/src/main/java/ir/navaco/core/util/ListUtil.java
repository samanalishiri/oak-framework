package ir.navaco.core.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 * Date 4/16/2018.
 */
public final class ListUtil {
    public static <T> List<T> safeList(List<T> unsafe) {
        return Optional.ofNullable(unsafe).orElse(Collections.emptyList());
    }
}
