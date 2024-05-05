package edu.java.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.Getter;

@Data
public class GitHubRepoResponse {
    @Getter @JsonProperty("name")
    private String name;

    @Getter  @JsonProperty("full_name")
    private String fullName;

    @Getter  @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @Getter  @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
}
