package com.possystem.mainapplication.modal;

import com.possystem.mainapplication.domain.StoreStatus;
import jakarta.annotation.Nullable;
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
public class ProductModal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String sku;
    private String description;
    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String image;
    //    category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModal category;
    @ManyToOne
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
