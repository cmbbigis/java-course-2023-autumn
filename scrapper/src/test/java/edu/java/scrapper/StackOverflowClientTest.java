package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import edu.java.Clients.StackOverflowClient;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StackOverflowClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void testFetchQuestion() {
        stubFor(get(urlEqualTo("/questions/123"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"title\":\"Question title\",\"link\":\"http://stackoverflow.com/questions/123\",\"creation_date\":\"2020-01-01T00:00:00Z\"}")));

        WebClient webClient = WebClient.create("http://localhost:8089");
        StackOverflowClient stackOverflowClient = new StackOverflowClient(webClient);

        StepVerifier.create(stackOverflowClient.fetchQuestion(123L))
            .expectNextMatches(question -> question.getTitle().equals("Question title") && question.getLink().equals("http://stackoverflow.com/questions/123"))
            .verifyComplete();
    }
}
