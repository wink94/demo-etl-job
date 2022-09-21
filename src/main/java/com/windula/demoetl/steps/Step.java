package com.windula.demoetl.steps;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The type Step.
 */
public abstract class Step {


    /**
     * The Date.
     */
    @Value("${DATE}")
    protected String date;


    /**
     * The constant logger.
     */
    protected static final Logger logger = LoggerFactory.getLogger(Step.class);

    private static final ConcurrentLinkedQueue<Integer> pageNumberQueue = new ConcurrentLinkedQueue<>();

    /**
     * Execute.
     *
     * @throws InterruptedException the interrupted exception
     */
    public abstract void execute() throws InterruptedException;

    /**
     * Get concurrent linked queue concurrent linked queue.
     *
     * @return the concurrent linked queue
     */
    protected ConcurrentLinkedQueue<Integer> getConcurrentLinkedQueue(){
        return pageNumberQueue;
    }
}
