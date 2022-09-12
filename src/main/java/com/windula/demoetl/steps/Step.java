package com.windula.demoetl.steps;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Step {


    @Value("${DATE}")
    protected String date;


    protected static final Logger logger = LoggerFactory.getLogger(Step.class);

    private static final ConcurrentLinkedQueue<Integer> pageNumberQueue = new ConcurrentLinkedQueue<>();

    public abstract void execute() throws InterruptedException;

    protected ConcurrentLinkedQueue<Integer> getConcurrentLinkedQueue(){
        return pageNumberQueue;
    }
}
