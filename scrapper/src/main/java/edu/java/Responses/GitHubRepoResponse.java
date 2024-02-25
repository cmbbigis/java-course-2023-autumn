package edu.java.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Getter;


public class GitHubRepoResponse {
    @Getter @JsonProperty("name")
    private String name;

    @Getter  @JsonProperty("full_name")
    private String fullName;

    @Getter  @JsonProperty("created_at")
    private OffsetDateTime createdAt;
}
