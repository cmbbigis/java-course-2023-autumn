package edu.java.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Link {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Id private Long id;
    private String url;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastCheckedAt = LocalDateTime.now();
    private String createdBy = "admin";

    public Link() {
    }

    public Link(Long id, String url, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.url = url;
        this.createdAt = createdAt;
        this.lastCheckedAt = LocalDateTime.MIN;
        this.createdBy = createdBy;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
