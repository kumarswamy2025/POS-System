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
// this modal represents the stock of specific product available in a specific branch
public class InventoryModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private BranchModal branch;  // the branch where the product is stocked. (Many to one relationship)
    @ManyToOne
    private ProductModal product; // the products being stocked (Many to one relationship)

    @Column(nullable = false)
    private Integer quantity;

    private LocalDateTime lastUpdated;

    @PreUpdate
    @PrePersist
    protected void onUpdate(){
        lastUpdated=LocalDateTime.now();
    }



}
