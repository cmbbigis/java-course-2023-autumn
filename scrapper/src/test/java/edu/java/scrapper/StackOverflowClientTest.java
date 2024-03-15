package edu.java.scrapper;

import edu.java.client.StackOverflowClient;
import edu.java.configuration.ClientConfiguration;
import org.junit.Test;
import reactor.test.StepVerifier;


public class StackOverflowClientTest {
    @Test
    public void testFetchQuestion() {
        StackOverflowClient stackOverflowClient = new StackOverflowClient(ClientConfiguration.stackOverflowWebClient());

        StepVerifier.create(stackOverflowClient.fetchQuestion(5408156L))
            .expectNextMatches(question ->
                question.getTitle().equals("How to drop a PostgreSQL database if there are active connections to it?")
                && question.getLink().equals(
"https://stackoverflow.com/questions/5408156/how-to-drop-a-postgresql-database-if-there-are-active-connections-to-it"))
            .verifyComplete();
    }
}
