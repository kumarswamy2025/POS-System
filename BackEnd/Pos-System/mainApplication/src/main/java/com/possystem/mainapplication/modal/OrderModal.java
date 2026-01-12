package com.possystem.mainapplication.modal;

import com.possystem.mainapplication.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class OrderModal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double totalAmount;
    private LocalDateTime createdAt;
    @ManyToOne
    @Column(name = "branchid")
    private BranchModal branch;
    @ManyToOne
    @Column(name = "cashierid")
    private UserModal cashier;
    private PaymentType paymentType;
    @OneToMany
    private List<OrderItemsModal> items;
    @ManyToOne
    @Column(name = "customerId")
    private CustomerModal customer;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

    }

}
