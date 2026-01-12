package com.possystem.mainapplication.modal;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class OrderItemsModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantity;
    private Double price;

    @ManyToOne
    @Column(name = "productId")
    private ProductModal product;
    @ManyToOne
    @Column(name = "orderId")
    private OrderModal order;
}
