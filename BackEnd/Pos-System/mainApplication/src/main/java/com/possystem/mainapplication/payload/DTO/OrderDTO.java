package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.domain.PaymentType;
import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.CustomerModal;
import com.possystem.mainapplication.modal.OrderItemsModal;
import com.possystem.mainapplication.modal.UserModal;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Double totalAmount;
    private LocalDateTime createdAt;

    private BranchDTO branch;
    private Long branchId;
    private Long customerId;
    private PaymentType paymentType;
    private UserDTO cashier;
    private CustomerModal customer;

    private List<OrderItemsDTO> items;

}
