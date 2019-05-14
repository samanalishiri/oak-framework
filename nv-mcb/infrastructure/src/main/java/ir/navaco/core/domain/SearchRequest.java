package ir.navaco.core.domain;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 * Date 5/5/2018.
 */
public class SearchRequest<F> {
    public final static SearchRequest DEFAULT = new SearchRequest();
    public final static SearchRequest UNLIMITED = new SearchRequest(0, Integer.MAX_VALUE);
    private int offset;
    private int limit;
    private F filter;

    public SearchRequest() {
        this(0, 100);
    }

    public SearchRequest(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public F getFilter() {
        return filter;
    }

    public void setFilter(F filter) {
        this.filter = filter;
    }
}
