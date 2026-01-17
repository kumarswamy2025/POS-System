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
    @ManyToOne
    private UserModal cashier;
    @ManyToOne
    private BranchModal branch;
    @Transient
    private List<PaymentSummary> PaymentSummaries;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductModal> topSellingProducts;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderModal> recentOrders;
    @OneToMany(mappedBy = "shiftReport",cascade = CascadeType.ALL)
    private List<RefundModal> refunds;
}
