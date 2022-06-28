package com.github.rcdomingos.themovies.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDto {

    private long id;

    private String name;

    private String description;

    @JsonProperty("release_year")
    private String releaseYear;


    public MovieDto() {
    }

    public MovieDto(long id, String name, String description, String releaseYear) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    public MovieDto(Movie savedMovie) {
        this.id = savedMovie.getId();
        this.name = savedMovie.getName();
        this.description = savedMovie.getDescription();
        this.releaseYear = savedMovie.getReleaseYear();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}
