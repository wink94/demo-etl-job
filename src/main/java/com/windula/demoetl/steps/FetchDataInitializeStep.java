package com.windula.demoetl.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The type Fetch data initialize step.
 */
@Component
public class FetchDataInitializeStep extends Step{

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchDataInitializeStep.class);
    @Override
    public void execute() throws InterruptedException {
        LOGGER.info("FetchDataInitializeStep started");
        // complex tasks can be handled in these steps this a simple implementation for demo purpose
        ConcurrentLinkedQueue<Integer> pageListQueue = getConcurrentLinkedQueue();
        for (int i = 0; i < 20; i++) {
            pageListQueue.add(i);
        }
        LOGGER.info("FetchDataInitializeStep ended");
    }
}
