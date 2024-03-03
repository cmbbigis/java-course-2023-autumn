package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import edu.java.client.StackOverflowClient;
import org.junit.Rule;
import org.junit.Test;
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

        StackOverflowClient stackOverflowClient = new StackOverflowClient();

        StepVerifier.create(stackOverflowClient.fetchQuestion(123L))
            .expectNextMatches(question -> question.getTitle().equals("Question title") && question.getLink().equals("http://stackoverflow.com/questions/123"))
            .verifyComplete();
    }
}
