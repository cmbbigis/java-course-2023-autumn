package edu.java.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


public class ListLinksResponse {
    private List<LinkResponse> links;
    private int size;

    @Schema(description = "links")
    public List<LinkResponse> getLinks() {
        return links;
    }

    public void setLinks(List<LinkResponse> links) {
        this.links = links;
    }

    @Schema(description = "size")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
