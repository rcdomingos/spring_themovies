package com.github.rcdomingos.themovies.model.entity;

import javax.persistence.*;

@Entity(name = "tb_movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    @Column(length = 510)
    private String description;

    @Column(name = "release_year", length = 4)
    private String releaseYear;


    public Movie() {
    }

    public Movie(String name, String description, String releaseYear) {
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    public Movie(long id, String name, String description, String releaseYear) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    public Movie(MovieDto movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.releaseYear = movie.getReleaseYear();
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
