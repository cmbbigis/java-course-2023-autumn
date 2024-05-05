package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SimpleIntegrationTest extends IntegrationTest {
    @Test
    public void testPostgresContainer() {
        assertTrue(POSTGRES.isRunning());
    }
}
