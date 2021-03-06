package demo.piano.stackexchangesearch.domain;


public class SearchResult {
    private Boolean hasMore;
    private Integer quotaMax;
    private Integer quotaRemaining;
    private Question[] items;

    public Question[] getItems() {
        return items;
    }

    public void setItems(Question[] items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(Integer quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }
}
