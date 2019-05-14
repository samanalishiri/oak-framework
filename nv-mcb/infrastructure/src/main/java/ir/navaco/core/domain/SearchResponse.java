package ir.navaco.core.domain;

import java.util.List;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 * Date 5/5/2018.
 */
public class SearchResponse<E> {
    private final long total;
    private final List<E> content;

    public SearchResponse(long total, List<E> content) {
        this.total = total;
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public List<E> getContent() {
        return content;
    }
}
