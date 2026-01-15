package com.possystem.mainapplication.payload.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.possystem.mainapplication.domain.PaymentType;
import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.OrderModal;
import com.possystem.mainapplication.modal.ShiftReportModal;
import com.possystem.mainapplication.modal.UserModal;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundDTO {
    private Long id;

    private OrderDTO orderModal;
    private Long orderId;
    private String reason;
    private Double amount;
//    private ShiftReportDTO shiftReport;
    private Long shiftReportId;
    private UserDTO cashier;
    private BranchDTO branch;
    private Long branchId;
    private LocalDateTime createdAt;
    private PaymentType paymentType;
}
