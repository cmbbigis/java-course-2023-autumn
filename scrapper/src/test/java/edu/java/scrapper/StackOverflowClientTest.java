package edu.java.scrapper;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import edu.java.client.StackOverflowClient;
import edu.java.configuration.ClientConfiguration;
import org.junit.Rule;
import org.junit.Test;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StackOverflowClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void testFetchQuestion() {
        stubFor(get(urlEqualTo("/questions/11227809"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"title\":\"Checking the license for Android SDK\",\"link\":\"http://stackoverflow.com/questions/78136214\",\"creation_date\":\"2020-01-01T00:00:00Z\"}")));

        StackOverflowClient stackOverflowClient = new StackOverflowClient(new ClientConfiguration());

        StepVerifier.create(stackOverflowClient.fetchQuestion(11227809L))
            .expectNextMatches(question -> question.getTitle().equals("Checking the license for Android SDK") && question.getLink().equals("http://stackoverflow.com/questions/78136214"))
            .verifyComplete();
    }
}
