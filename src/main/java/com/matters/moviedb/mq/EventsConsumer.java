package com.matters.moviedb.mq;

import com.matters.moviedb.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 Consumer to consume events
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventsConsumer {

    private final MovieService movieService;

    @SneakyThrows
    @KafkaListener(topics = "movie")
    public void consumeEvent(Integer page) {
        movieService.processMovies(page);
        Thread.sleep(1000); //Pause for 1 second, to avoid block from movieDB
    }

    /**
     * I wanted to use async approach, but movieDB is blocking requests with 403 response if there are too many
     */
    private void consumeAsync(Integer page) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.submit(()-> movieService.processMovies(page));
    }

}
