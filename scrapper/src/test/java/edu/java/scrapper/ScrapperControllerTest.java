package edu.java.scrapper;

import edu.java.service.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ScrapperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("jpaLinkService")
    private LinkService jpaLinkService;

    @Test
    public void testPostUpdate() throws Exception {
        when(jpaLinkService.listAll(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/links")
                .header("Tg-Chat-Id", 1)
                .contentType("application/json"))
            .andExpect(status().isOk());
    }
}
