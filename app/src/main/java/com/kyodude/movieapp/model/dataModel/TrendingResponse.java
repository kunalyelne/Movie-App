package com.kyodude.movieapp.model.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrendingResponse {
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("trendingResults")
    @Expose
    private List<TrendingResult> trendingResults = null;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TrendingResult> getTrendingResults() {
        return trendingResults;
    }

    public void setTrendingResults(List<TrendingResult> trendingResults) {
        this.trendingResults = trendingResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
