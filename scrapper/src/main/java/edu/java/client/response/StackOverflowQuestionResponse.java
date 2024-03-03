package edu.java.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.Getter;

@Data
public class StackOverflowQuestionResponse {
    @Getter @JsonProperty("title")
    private String title;

    @Getter @JsonProperty("link")
    private String link;

    @Getter @JsonProperty("creation_date")
    private OffsetDateTime creationDate;
}
