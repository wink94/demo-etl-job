package com.windula.demoetl.steps;

import com.windula.demoetl.config.ThreadPoolConfig;
import com.windula.demoetl.service.BeerService;
import com.windula.demoetl.service.DataFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Fetch data step.
 */
@Component
public class FetchDataStep extends Step {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchDataStep.class);
    @Autowired
    private ExecutorService executorService;

    @Autowired
    private BeerService beerService;
    @Autowired
    private DataFetchService dataFetchService;

    public void execute() throws InterruptedException {
        LOGGER.info("FetchDataStep started");
        ConcurrentLinkedQueue<Integer> pageListQueue = getConcurrentLinkedQueue();
        int maxThreadCount = ThreadPoolConfig.getConcurrentTasksCount();

        try {
            List<Callable<Object>> runnableTasks = new ArrayList<>(maxThreadCount);
            for (int i = 0; i < maxThreadCount; i++) {
                FetchDataRunnable dataFetchRunnable = new FetchDataRunnable(dataFetchService, beerService);
                dataFetchRunnable.setConcurrentLinkedQueue(pageListQueue);
                dataFetchRunnable.setDate(LocalDate.parse(date));
                runnableTasks.add(Executors.callable(dataFetchRunnable));
            }
            executorService.invokeAll(runnableTasks);
            LOGGER.info("FetchDataStep started");
        } catch (Exception e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            logger.error("data fetch failed due to thread interruption", e);
            throw new InterruptedException("data fetch failed due to thread interruption");
        }
    }
}
