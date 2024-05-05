package edu.java.domain.entity;

import java.time.LocalDateTime;
import lombok.Data;


@Data
public class Chat {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String createdBy;

    public Chat() {
        id = 0L;
        name = "";
        createdAt = LocalDateTime.MIN;
        createdBy = "";
    }

    public Chat(Long id, String name, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
