package com.example.fwqi.dudu.data;

/**
 * Created by leehong on 2015/10/13.
 */
public class PageInfo {
    // The last index of the last product in one page.
    private String lastId = null;
    // The product count will be show in one page.
    private int pageSize = 0;
    // Determine whether has more product data in next page.
    private int hasMore = 0;

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }
}
