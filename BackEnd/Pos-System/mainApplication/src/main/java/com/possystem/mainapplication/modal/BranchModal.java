package com.possystem.mainapplication.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class BranchModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String phone;
    @Column(nullable = false)
    private String email;
    @ElementCollection
    private List<String> workingDays;

    private LocalTime openTime;
    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //   many branches belongs to one branch
//    one store have many branches
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreModal store;

//   one branch have one manager

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "manager_id")
    private UserModal manager;


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
