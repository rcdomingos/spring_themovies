package com.github.rcdomingos.themovies.controller;

import com.github.rcdomingos.themovies.model.entity.MovieDto;
import com.github.rcdomingos.themovies.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService service;

    @PostMapping
    public ResponseEntity<MovieDto> createNewMovie(@RequestBody MovieDto movie) {
        log.debug("createNewMovie() - chamando serviço");
        MovieDto movieDto = service.addNewMovie(movie);
        return ResponseEntity.status(201).body(movieDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable Long id) {
        log.debug("findMovieById() - chamando serviço");
        return ResponseEntity.ok(service.getMovieById(id));
    }

    @GetMapping
    public ResponseEntity<Page<MovieDto>> getAllMovies(@RequestParam(value = "release_year", required = false) String releaseYear,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @RequestParam(value = "sort_by", defaultValue = "id") String sortBy) {
        log.debug("findMovieById() - chamando serviço, releaseYear: {}", releaseYear);
        if (releaseYear == null) {
            return ResponseEntity.ok(service.getAllMovies(page, size, sortBy));
        } else {
            return ResponseEntity.ok(service.getAllMoviesByReleaseYear(releaseYear, page, size, sortBy));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        log.debug("deleteMovie() - chamando o serviçor");
        service.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movie) {
        log.debug("updateMovie() - chamando o serviço");
        return ResponseEntity.ok(service.updateMovieById(id, movie));
    }


    @PostMapping("/intialLoad")
    public ResponseEntity<Void> initialMovies() {
        log.debug("initialMovies() - chamando serviço");
        service.loadInitialMovies();
        return ResponseEntity.ok().build();
    }


}


