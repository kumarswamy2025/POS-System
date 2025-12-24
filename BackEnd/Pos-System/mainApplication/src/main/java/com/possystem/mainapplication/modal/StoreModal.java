package com.possystem.mainapplication.modal;

import com.possystem.mainapplication.domain.StoreStatus;
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
public class StoreModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    private String brand;
    @OneToOne    // one storeAdmin belongs to one store
    private UserModal storeAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String storeType;
    private StoreStatus status;
    private StoreAddress contact;

// ✔️ Runs only once, when the entity is first persisted.
    @PrePersist
    protected  void onCreate(){
        createdAt=LocalDateTime.now();
        status =StoreStatus.PENDING;
    }

// ✔️ Runs every time the entity is updated.
    @PreUpdate
    protected  void onUpdate(){
        updatedAt=LocalDateTime.now();
    }


}
