package edu.java.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.net.URI;

public class LinkResponse {
    private long id;
    private URI url;

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
}
