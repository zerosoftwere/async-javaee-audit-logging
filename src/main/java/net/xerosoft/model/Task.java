package net.xerosoft.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
@NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    enum Status {
        INITIATED, COMPLETED, FAILED
    }
}
