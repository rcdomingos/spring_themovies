package com.github.rcdomingos.themovies.service;

import com.github.rcdomingos.themovies.model.domain.MoviesTheMovie;
import com.github.rcdomingos.themovies.model.entity.Movie;
import com.github.rcdomingos.themovies.model.entity.MovieDto;
import com.github.rcdomingos.themovies.model.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository repository;

    @Autowired
    private TheMovieDBServive theMovieDBServive;

    public MovieDto addNewMovie(MovieDto movie) {
        log.info("addNewMovie() - INICIO - Salvando o filme: {}", movie.getName());
        Movie savedMovie = repository.save(new Movie(movie));
        log.info("addNewMovie() - FIM - Filme salvo com o ID {}", savedMovie.getId());
        return new MovieDto(savedMovie);
    }

    public MovieDto getMovieById(Long movieId) {
        log.info("getMovieById() - INICIO - Buscando o filme de ID: {}", movieId);
        Movie movie = getValidMovieById(movieId);
        log.info("getMovieById() - FIM - Filme {} encontrado", movie.getDescription());
        return new MovieDto(movie);
    }

    public Page<MovieDto> getAllMovies(int page, int size, String sortBy) {
        log.info("getAllMovies() - INICIO - Buscando todos os filmes da pagina {}", page);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Movie> result = repository.findAll(pageable);
        return result.map(MovieDto::new);
    }
    public Page<MovieDto> getAllMoviesByReleaseYear(String releaseYear, int page, int size, String sortBy) {
        log.info("getAllMoviesByReleaseYear() - INICIO - Buscando os filmes com release_year {} da pagina {}", releaseYear, page);
        Pageable sortedByName = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Movie> allByReleaseYear = repository.findAllByReleaseYear(releaseYear, sortedByName);
        return allByReleaseYear.map(MovieDto::new);
    }

    public void deleteMovieById(Long movieId) {
        log.info("deleteMovieById() - INICIO - Excluindo o filme de ID: {}", movieId);
        Movie movie = getValidMovieById(movieId);
        repository.delete(movie);
        log.info("deleteMovieById() - FIM - Filme excluido com sucesso");
    }

    public MovieDto updateMovieById(Long movieId, MovieDto movie) {
        log.info("updateMovieById() - INICIO - Atualizando o filme de ID: {}", movieId);
        Movie movieEntity = getValidMovieById(movieId);
        movieEntity.setDescription(movie.getDescription() != null ? movie.getDescription() : movieEntity.getDescription());
        movieEntity.setName(movie.getName() != null ? movie.getName() : movieEntity.getName());
        movieEntity.setReleaseYear(movie.getReleaseYear() != null ? movie.getReleaseYear() : movieEntity.getReleaseYear());
        repository.save(movieEntity);
        log.info("updateMovieById() - FIM - Informações atualizadas");
        return new MovieDto(movieEntity);
    }

    public void loadInitialMovies() {
        log.info("loadInitialMovies() - INICIO - Cadastrando filmes da API");
        List<MoviesTheMovie> moviesFromAPI = theMovieDBServive.getMoviesFromAPI();

        List<Movie> moviesTheList = moviesFromAPI.stream().map(moviesTheMovie -> new Movie(
                moviesTheMovie.getTitle(),
                moviesTheMovie.getOverview(),
                getYearFromDate(moviesTheMovie.getReleaseDate())
        )).collect(Collectors.toList());

        List<Movie> movies = repository.saveAll(moviesTheList);
        log.info("loadInitialMovies() FIM - foram salvos {} filmes", movies.size());
    }

    private String getYearFromDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);
            return String.valueOf(localDate.getYear());
        } catch (DateTimeParseException e) {
            log.error("getYearFromDate() - ERRO - Não foi possivel fazer o parse da data devido ao erro: {}", e.getMessage());
            return null;
        }
    }

    private Movie getValidMovieById(long id) {
        Optional<Movie> optionalMovie = repository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            log.warn("getValidMovieById() - AVISO - Filme com ID: {} não existe na base", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id informado não localizado");
        }
    }

}
