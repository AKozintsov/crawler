package com.matters.moviedb.movie;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 Properties configuration
 */
@Getter
@Configuration
public class MovieDbProperties {

    @Value("${movie-db.movie.url}")
    private String movieUrl;

    @Value("${movie-db.movie.css.selector.page-number}")
    private String moviePageNumberCssSelector;

    @Value("${movie-db.movie.css.selector.card}")
    private String movieCardCssSelector;

    @Value("${movie-db.movie.css.selector.title}")
    private String movieTitleCssSelector;

    @Value("${movie-db.movie.css.selector.image}")
    private String moviePictureCssSelector;

    @Value("${movie-db.movie.css.attribute.title}")
    private String movieTitleAttribute;

    @Value("${movie-db.movie.css.attribute.image}")
    private String movieImageAttribute;


}
