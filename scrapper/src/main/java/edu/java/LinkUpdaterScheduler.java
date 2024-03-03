package edu.java;

import lombok.extern.java.Log;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

@Log
public class LinkUpdaterScheduler {
    private final static Logger LOGGER = LogManager.getLogger();

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        LOGGER.log(Level.ALL, "Обновление ссылок...");
    }
}
