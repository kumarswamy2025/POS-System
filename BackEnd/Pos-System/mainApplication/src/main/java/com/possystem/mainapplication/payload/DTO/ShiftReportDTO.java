package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.modal.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShiftReportDTO {
    private Long id;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private Double totalSale;
    private Double totalRefund;
    private Double netSale;
    private int totalOrders;

    private UserDTO cashier;
    private Long cashierId;

    private BranchDTO branch;
    private Long branchId;

    private List<PaymentSummary> paymentSummaries;

    private List<ProductDTO> topSellingProducts;

    private List<OrderDTO> recentOrders;

    private List<RefundDTO> refunds;
}
