package com.matters.moviedb.movie.crawler;

import com.matters.moviedb.document.DocumentService;
import com.matters.moviedb.movie.Movie;
import com.matters.moviedb.movie.MovieDbProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 Service to provide Collection of Movies for page
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MovieCrawler {

    private final DocumentService documentService;

    private final MovieParser movieParser;

    private final MovieDbProperties movieDbProperties;

    public List<Movie> getMoviesForPage(Integer page) {
        //get HTML content for movie page
        var contentPageId = String.format(movieDbProperties.getMoviePageNumberCssSelector(), page);
        var moviePage = getMoviePage(page);

        //get HTML content for movies
        var moviePageCards = moviePage
                .getElementById(contentPageId)
                .getElementsByClass(movieDbProperties.getMovieCardCssSelector());

        //Extract title/image for movie from HTML content
        return movieParser.parseMovies(moviePageCards);
    }

    private Element getMoviePage(Integer page) {
        var url = String.format(movieDbProperties.getMovieUrl(), page);
        return documentService.getDocument(url).body();
    }

}
