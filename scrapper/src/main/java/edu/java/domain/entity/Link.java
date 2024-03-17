package edu.java.domain.entity;

import java.time.LocalDateTime;
import lombok.Data;


@Data
public class Link {
    private Long id;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime lastCheckedAt;
    private String createdBy;

    public Link() {
    }

    public Link(Long id, String url, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.url = url;
        this.createdAt = createdAt;
        this.lastCheckedAt = LocalDateTime.MIN;
        this.createdBy = createdBy;
    }
}
