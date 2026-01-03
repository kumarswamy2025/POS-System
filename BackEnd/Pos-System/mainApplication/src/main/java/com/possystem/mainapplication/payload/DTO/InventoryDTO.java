package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.ProductModal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long id;
    private BranchDTO branch;
    private Long branchId;
    private ProductDTO product;
    private Long productId;
    private Integer quantity;
    private LocalDateTime lastUpdated;
}
