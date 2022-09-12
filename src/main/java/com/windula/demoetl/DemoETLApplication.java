package com.windula.demoetl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoETLApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoETLApplication.class);

	public static void main(String[] args) {
		try {
			LOGGER.info("application is starting....");
			ConfigurableApplicationContext ctx = SpringApplication.run(DemoETLApplication.class, args);
			LOGGER.info("application is shutting down....");
			SpringApplication.exit(ctx, () -> 0);
		} catch (Exception exception) {
			LOGGER.error("{} application concluded", exception);
			System.exit(1);
		}
	}

}
