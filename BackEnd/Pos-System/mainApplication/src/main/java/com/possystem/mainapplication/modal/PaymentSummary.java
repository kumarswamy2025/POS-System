package com.possystem.mainapplication.modal;

import com.possystem.mainapplication.domain.PaymentType;
import lombok.Data;

@Data
public class PaymentSummary {
    private PaymentType type;
    private Double totalAmount;
    private int transactionCount;
    private Double percentage;



}
