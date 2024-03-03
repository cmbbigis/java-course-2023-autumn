package edu.java.bot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostUpdate() throws Exception {
        mockMvc.perform(post("/updates")
                .contentType("application/json")
                .content("{\"id\":1,\"url\":\"http://example.com\",\"description\":\"test\",\"tgChatIds\":[1,2,3]}"))
            .andExpect(status().isOk());
    }
}
