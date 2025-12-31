package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.modal.StoreModal;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String sku;
    private String description;
    private double mrp;
    private double sellingPrice;
    private String brand;
    private String image;
    private CategoryDTO categoryDTO;
    private Long categoryId;
    private Long storeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
