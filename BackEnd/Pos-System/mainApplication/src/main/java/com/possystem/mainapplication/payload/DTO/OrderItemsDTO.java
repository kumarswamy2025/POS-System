package com.possystem.mainapplication.payload.DTO;


import com.possystem.mainapplication.modal.OrderModal;
import com.possystem.mainapplication.modal.ProductModal;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDTO {
    private Long id;
    private Integer quantity;
    private Double price;
    private ProductDTO product;
    private Long orderId;
}
