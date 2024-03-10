package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import edu.java.client.GitHubClient;
import edu.java.configuration.ClientConfiguration;
import org.junit.Rule;
import org.junit.Test;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GitHubClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void testFetchRepository() {
        stubFor(get(urlEqualTo("/repos/cmbbigis/git-rules"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"name\":\"git-rules\",\"full_name\":\"cmbbigis/git-rules\",\"created_at\":\"2021-11-03T12:00:39Z\"}")));

        GitHubClient gitHubClient = new GitHubClient(new ClientConfiguration());

        StepVerifier.create(gitHubClient.fetchRepository("cmbbigis", "git-rules"))
            .expectNextMatches(repo -> repo.getName().equals("git-rules") && repo.getFullName().equals("cmbbigis/git-rules"))
            .verifyComplete();
    }
}
