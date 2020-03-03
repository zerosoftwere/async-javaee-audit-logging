package net.xerosoft.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NamedQuery(name = "AuditLog.findAll", query ="SELECT q FROM AuditLog q")
@NamedQuery(name = "AuditLog.findById", query ="SELECT q FROM AuditLog q WHERE q.id = :id")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;
    private String event;
    private Status status;
    private String description;
    private LocalDateTime createAt;

    @PrePersist
    private void onCreate() {
        createAt = LocalDateTime.now();
    }

    public enum Status {
        SUCCESS, FAIL
    }
}
