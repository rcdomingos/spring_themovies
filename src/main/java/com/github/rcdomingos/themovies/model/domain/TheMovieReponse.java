package com.github.rcdomingos.themovies.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TheMovieReponse {

    private Long page;

    private List<MoviesTheMovie> results;

    @JsonProperty("total_pages")
    private Long totalPage;

    @JsonProperty("total_results")
    private Long totalResults;


    public TheMovieReponse() {
    }

    public TheMovieReponse(Long page, List<MoviesTheMovie> results, Long totalPage, Long totalResults) {
        this.page = page;
        this.results = results;
        this.totalPage = totalPage;
        this.totalResults = totalResults;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<MoviesTheMovie> getResults() {
        return results;
    }

    public void setResults(List<MoviesTheMovie> results) {
        this.results = results;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }
}
