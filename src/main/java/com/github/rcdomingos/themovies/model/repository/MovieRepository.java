package com.github.rcdomingos.themovies.model.repository;

import com.github.rcdomingos.themovies.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
