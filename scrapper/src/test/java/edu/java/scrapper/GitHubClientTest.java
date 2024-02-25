package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import edu.java.Clients.GitHubClient;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GitHubClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void testFetchRepository() {
        stubFor(get(urlEqualTo("/repos/user/repo"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"name\":\"repo\",\"full_name\":\"user/repo\",\"created_at\":\"2020-01-01T00:00:00Z\"}")));

        WebClient webClient = WebClient.create("http://localhost:8089");
        GitHubClient gitHubClient = new GitHubClient(webClient);

        StepVerifier.create(gitHubClient.fetchRepository("user", "repo"))
            .expectNextMatches(repo -> repo.getName().equals("repo") && repo.getFullName().equals("user/repo"))
            .verifyComplete();
    }
}
