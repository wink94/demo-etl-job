package com.windula.demoetl.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.windula.demoetl.model.Beer;
import com.windula.demoetl.service.BeerService;
import com.windula.demoetl.service.DataFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The type Fetch data runnable.
 */
public class FetchDataRunnable implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchDataRunnable.class);
    private ConcurrentLinkedQueue<Integer> concurrentLinkedQueue;

    private DataFetchService dataFetchService;

    private BeerService beerService;
    private LocalDate date;

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets concurrent linked queue.
     *
     * @param concurrentLinkedQueue the concurrent linked queue
     */
    public void setConcurrentLinkedQueue(ConcurrentLinkedQueue<Integer> concurrentLinkedQueue) {
        this.concurrentLinkedQueue = concurrentLinkedQueue;
    }

    /**
     * Instantiates a new Fetch data runnable.
     *
     * @param dataFetchService the data fetch service
     * @param beerService      the beer service
     */
    public FetchDataRunnable(DataFetchService dataFetchService, BeerService beerService) {
        this.dataFetchService = dataFetchService;
        this.beerService = beerService;
    }

    @Override
    public void run() {
        Integer page;

        while ((page = concurrentLinkedQueue.poll()) != null) {
            LOGGER.info("Page " + page);
            try {
                JsonNode beer = dataFetchService.getData(page);
                processAndPersistData(beer);

            } catch (Exception e) {
                LOGGER.error(" error during data fetch ", e);
            }
        }
    }

    private void processAndPersistData(JsonNode jsonArrayNode) {

        if (jsonArrayNode.isArray()) {
            int i = 0;
            while (jsonArrayNode.get(i) != null) {
                JsonNode jsonNode = jsonArrayNode.get(i);
                Beer beer = new Beer(jsonNode.get("id").asInt(), jsonNode.get("name").asText(), jsonNode.get("tagline").asText(), jsonNode.get("first_brewed").asText(), jsonNode.get("description").asText(), jsonNode.get("image_url").asText());
                beerService.addBeer(beer);
                i++;
            }
        }

    }
}
