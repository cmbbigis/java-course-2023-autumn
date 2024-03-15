package edu.java.scrapper;

import edu.java.client.GitHubClient;
import edu.java.configuration.ClientConfiguration;
import org.junit.Test;
import reactor.test.StepVerifier;


public class GitHubClientTest {
    @Test
    public void testFetchRepository() {
        GitHubClient gitHubClient = new GitHubClient(ClientConfiguration.gitHubWebClient());

        StepVerifier.create(gitHubClient.fetchRepository("cmbbigis", "git-rules"))
            .expectNextMatches(repo -> repo.getName().equals("git-rules") && repo.getFullName().equals("cmbbigis/git-rules"))
            .verifyComplete();
    }
}
