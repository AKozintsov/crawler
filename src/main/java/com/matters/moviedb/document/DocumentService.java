package com.matters.moviedb.document;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 Service to provide Document(HTML Content) using Jsoup library
 */
@Slf4j
@Service
public class DocumentService {

    public Document getDocument(String url) {
        Document document = null;
        try {
            document = Jsoup
                    .connect(url)
                    .userAgent(HttpConnection.DEFAULT_UA)
                    .execute().parse();
        } catch (Exception e) {
            log.error("Unable to load HTML Content :{}", e.getMessage()); //(e.g. 403 when too many requests)
        }
        return document;
    }
}
