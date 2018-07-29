package com.elepy.dao;

public class QuerySetup {

    private final String query;
    private final String sortBy;
    private final SortOption sortOption;

    private final long pageNumber;
    private final int pageSize;

    public QuerySetup(String query, String sortBy, SortOption sortOption, long pageNumber, int pageSize) {
        this.query = query;
        this.sortBy = sortBy;
        this.sortOption = sortOption;
        if (pageNumber == 0) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
        if (pageSize == 0) {
            this.pageSize = Integer.MAX_VALUE;
        } else {

            this.pageSize = pageSize;
        }

    }

    public String getQuery() {
        return query;
    }

    public String getSortBy() {
        return sortBy;
    }

    public SortOption getSortOption() {
        return sortOption;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }
}