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
public class Chat {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Id private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String createdBy;

    public Chat() {
        id = 0L;
        name = "";
        createdAt = LocalDateTime.now();
        createdBy = "";
    }

    public Chat(Long id, String name, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
