package edu.java.bot.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class RemoveLinkRequest {
    private String link;

    @Schema(description = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
