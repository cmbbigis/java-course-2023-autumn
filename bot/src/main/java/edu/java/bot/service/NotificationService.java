package edu.java.bot.service;

import edu.java.bot.api.request.LinkUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UpdateHandler updateHandler;


    public void handleNotification(LinkUpdate update) {
        updateHandler.handleUpdate(update);
    }
}
