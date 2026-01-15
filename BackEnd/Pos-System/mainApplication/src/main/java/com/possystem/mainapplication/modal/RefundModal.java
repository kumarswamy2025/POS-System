package com.possystem.mainapplication.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.possystem.mainapplication.domain.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RefundModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private OrderModal orderModal;
    private String reason;
    private Double amount;
    @ManyToOne
    @JsonIgnore
    private ShiftReportModal shiftReport;
    @ManyToOne
    private UserModal cashier;
    @ManyToOne
    private BranchModal branch;
    private LocalDateTime createdAt;

    private PaymentType paymentType;


    // ✔️ Runs only once, when the entity is first persisted.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

    }


}
