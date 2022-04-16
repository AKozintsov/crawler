package com.matters.moviedb.movie.crawler;

import com.matters.moviedb.movie.Movie;
import com.matters.moviedb.movie.MovieDbProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 Component to extract Movie data(Title, Image) from Elements(HTML Content)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MovieParser {

    private final MovieDbProperties movieDbProperties;

    public List<Movie> parseMovies(Elements elements) {
        List<Movie> movies = new ArrayList<>();
        elements.forEach(element -> {
            try {
                var movie = parseMovie(element);
                movies.add(movie);
            } catch (Exception e) {
                log.error("Unable to parse movie: {}", e.getMessage());
            }
        });
        return movies;
    }

    /**
     * I use Optional, because of some content is missing(e.g. empty image for movie)
     * @param element - HTML Content element
     * @return Movie
     */
    private Movie parseMovie(Element element) {
        var movieTitle = Optional.of(element)
                .map(el-> el.selectFirst(movieDbProperties.getMovieTitleCssSelector()))
                .map(el-> el.attr(movieDbProperties.getMovieTitleAttribute()))
                .orElse("empty");

        var movieImage = Optional.of(element)
                .map(el-> el.selectFirst(movieDbProperties.getMoviePictureCssSelector()))
                .map(el-> el.attr(movieDbProperties.getMovieImageAttribute()))
                .orElse("empty");

        return new Movie(movieTitle, movieImage);
    }
}
