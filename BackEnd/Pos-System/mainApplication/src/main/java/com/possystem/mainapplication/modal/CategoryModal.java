package com.possystem.mainapplication.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class CategoryModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreModal store;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ✔️ Runs only once, when the entity is first persisted.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

    }

    // ✔️ Runs every time the entity is updated.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
