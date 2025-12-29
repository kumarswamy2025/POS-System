package com.possystem.mainapplication.modal;

import com.possystem.mainapplication.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class StoreModal {
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default

    private List<UserModal> users = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String brand;
    @OneToOne    // one storeAdmin belongs to one store
    @JoinColumn(name = "store_admin_id")
    private UserModal storeAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String storeType;
    private StoreStatus status;
    @Embedded
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
