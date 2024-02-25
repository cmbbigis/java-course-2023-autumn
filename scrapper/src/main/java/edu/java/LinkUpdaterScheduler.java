package edu.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public class LinkUpdaterScheduler {

    private final static Logger LOGGER = LogManager.getLogger();

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        LOGGER.trace("Обновление ссылок...");
    }
}
