package com.possystem.mainapplication.modal;

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
public class ShiftReportModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
