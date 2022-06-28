package com.github.rcdomingos.themovies.service;

import com.github.rcdomingos.themovies.model.domain.MoviesTheMovie;
import com.github.rcdomingos.themovies.model.domain.TheMovieReponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheMovieDBServive {

    private static final Logger log = LoggerFactory.getLogger(TheMovieDBServive.class);

    public List<MoviesTheMovie> getMoviesFromAPI() {
        log.info("getMoviesFromAPI() - INICIO - iniciando requisição para api the movie db");

        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=ac05d799a404adfe064697a1e71c89da";
        List<MoviesTheMovie> theMovieList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TheMovieReponse> response = restTemplate.getForEntity(url, TheMovieReponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            theMovieList = response.getBody().getResults();
        } else {
            log.warn("getMoviesFromAPI() - AVISO - Consulta sem sucesso, status code retornado: {}", response.getStatusCode());
        }
        log.info("getMoviesFromAPI() - FIM - Filmes encontrados {}", theMovieList.size());
        return theMovieList;
    }

}
