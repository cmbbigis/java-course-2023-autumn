package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class SimpleIntegrationTest extends IntegrationTest {
    @Test
    public void testPostgresContainer() {
        assertTrue(POSTGRES.isRunning());
    }
}
