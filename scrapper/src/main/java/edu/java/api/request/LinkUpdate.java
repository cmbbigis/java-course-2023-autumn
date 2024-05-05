package edu.java.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.net.URI;
import java.util.List;

public class LinkUpdate {
    private long id;
    private URI url;
    private String description;
    private List<Long> tgChatIds;

    @Schema(description = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Schema(description = "url")
    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    @Schema(description = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Schema(description = "tgChatIds")
    public List<Long> getTgChatIds() {
        return tgChatIds;
    }

    public void setTgChatIds(List<Long> tgChatIds) {
        this.tgChatIds = tgChatIds;
    }
}
