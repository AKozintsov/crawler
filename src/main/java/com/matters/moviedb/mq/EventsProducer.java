package com.matters.moviedb.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 Producer to produce events
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventsProducer {

    private final KafkaTemplate<String, Integer> kafkaTemplate;

    public void produceEvent(Integer page) {
        kafkaTemplate.send("movie", page);
    }

    /**
     * Creating events to parse 1-500 pages of movieDB
     */
    @PostConstruct
    public void startCrawler() {
        for (int i=1; i<=500; i++) {
            produceEvent(i);
        }
    }
}
