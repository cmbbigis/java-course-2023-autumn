package edu.java.service.jdbc;

import edu.java.api.request.LinkUpdate;
import edu.java.client.BotClient;
import edu.java.client.GitHubClient;
import edu.java.client.StackOverflowClient;
import edu.java.client.response.GitHubRepoResponse;
import edu.java.client.response.StackOverflowQuestionResponse;
import edu.java.domain.entity.Link;
import edu.java.domain.jdbc.LinkDao;
import edu.java.service.LinkUpdater;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcLinkUpdater implements LinkUpdater {
    @Autowired
    private LinkDao linkDao;
    @Autowired
    private GitHubClient gitHubClient;
    @Autowired
    private StackOverflowClient stackOverflowClient;
    @Autowired
    private BotClient botClient;

    private final int twentyFour = 24;
    private final int five = 5;
    private final int four = 4;
    private final int three = 3;

    @Override
    public int update() throws URISyntaxException {
        List<Link> links = linkDao.findLinksNotCheckedSince(Duration.ofHours(twentyFour));

        int updatedLinksCount = 0;

        for (Link link : links) {
            if (link.getUrl().startsWith("https://github.com/")) {
                String[] parts = link.getUrl().split("/");
                if (parts.length >= five) {
                    String user = parts[three];
                    String repo = parts[four];
                    GitHubRepoResponse response = gitHubClient.fetchRepository(user, repo).block();
                    if (response != null
                        && response.getUpdatedAt().toLocalDateTime().isAfter(link.getLastCheckedAt())) {
                        var update = new LinkUpdate();
                        update.setUrl(new URI(link.getUrl()));
                        update.setId(link.getId());
                        update.setDescription("");
                        update.setTgChatIds(new ArrayList<>());
                        botClient.postUpdate(update);
                        updatedLinksCount++;
                    }
                }
            } else if (link.getUrl().startsWith("https://stackoverflow.com/questions/")) {
                String[] parts = link.getUrl().split("/");
                if (parts.length >= five) {
                    Long questionId = Long.parseLong(parts[four]);
                    StackOverflowQuestionResponse response = stackOverflowClient.fetchQuestion(questionId).block();
                    if (response != null
                        && response.getUpdatedAt().toLocalDateTime().isAfter(link.getLastCheckedAt())) {
                        var update = new LinkUpdate();
                        update.setUrl(new URI(link.getUrl()));
                        update.setId(link.getId());
                        update.setDescription("");
                        update.setTgChatIds(new ArrayList<>());
                        botClient.postUpdate(update);
                        updatedLinksCount++;
                    }
                }
            }

            link.setLastCheckedAt(LocalDateTime.now());
            linkDao.update(link);
        }

        return updatedLinksCount;
    }
}
