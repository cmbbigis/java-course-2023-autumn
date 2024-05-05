package edu.java.service.jpa;

import edu.java.domain.entity.Link;
import edu.java.domain.jpa.LinkRepository;
import edu.java.service.LinkUpdater;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JpaLinkUpdater implements LinkUpdater {
    @Autowired
    private LinkRepository linkRepository;

    private final WebClient webClient = WebClient.create();

    private final int twentyFour = 24;

    @Override
    public int update() throws URISyntaxException {
        List<Link> links = linkRepository.findByLastCheckedAtBefore(LocalDateTime.now().minusHours(twentyFour));

        int updatedLinksCount = 0;

        for (Link link : links) {
            try {
                WebClient.ResponseSpec responseSpec = webClient.get().uri(link.getUrl()).retrieve();

                LocalDateTime lastModified = responseSpec.toBodilessEntity()
                    .map(response -> response.getHeaders().getLastModified())
                    .map(millis -> Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()))
                    .map(ZonedDateTime::toLocalDateTime)
                    .block();

                if (lastModified != null && lastModified.isAfter(link.getLastCheckedAt())) {
                    updatedLinksCount++;
                }

                link.setLastCheckedAt(LocalDateTime.now());
                linkRepository.save(link);
            } catch (Exception e) {
                System.err.println("Failed to check link: " + link.getUrl());
            }
        }

        return updatedLinksCount;
    }
}
