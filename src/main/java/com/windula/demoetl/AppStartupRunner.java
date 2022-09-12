package com.windula.demoetl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    private ETLProcess etlProcess;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Application started with option names : {}", args.getOptionNames());
        etlProcess.startETLProcess();
    }
}
