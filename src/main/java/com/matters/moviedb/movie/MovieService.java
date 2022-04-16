package com.matters.moviedb.movie;

import com.matters.moviedb.dao.MovieEntity;
import com.matters.moviedb.dao.MovieRepository;
import com.matters.moviedb.movie.crawler.MovieCrawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 Service for movies aggregation and persisting
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieCrawler movieCrawler;

    private final MovieRepository movieRepository;

    public void processMovies(Integer page) {
        List<Movie> movies;
        try {
            movies = movieCrawler.getMoviesForPage(page);
        } catch (Exception e) {
            log.error("Unable to crawl page: {}, error: {}", page, e.getMessage());
            return;
        }
        movieRepository.saveAll(movieToEntity(movies, page));
        log.info("Successfully saved movies for page {}, {} ", page, movies);
    }

    private List<MovieEntity> movieToEntity(List<Movie> movies, Integer pageNumber) {
        return movies.stream()
                .map(movie -> MovieEntity.builder()
                        .pageNumber(pageNumber)
                        .title(movie.title())
                        .image(movie.image())
                        .build())
                .collect(Collectors.toList());
    }


}
