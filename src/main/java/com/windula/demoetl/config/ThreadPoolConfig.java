package com.windula.demoetl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    private static final int CONCURRENT_TASKS_COUNT = 8;

    public static int getConcurrentTasksCount() {
        return CONCURRENT_TASKS_COUNT;
    }

    /**
     * This method creates a ThreadPoolTaskExecutor with given number of threads
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    public ExecutorService calculationTaskExecutor() {
        return Executors.newFixedThreadPool(CONCURRENT_TASKS_COUNT);
    }
}
