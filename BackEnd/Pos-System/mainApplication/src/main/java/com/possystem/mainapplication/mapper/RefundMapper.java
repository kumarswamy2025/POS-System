package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.RefundModal;
import com.possystem.mainapplication.payload.DTO.RefundDTO;

public class RefundMapper {

    public static RefundDTO toDTO(RefundModal refund){
        RefundDTO refundDTO =RefundDTO.builder()
                .id(refund.getId())
                .orderModal(OrderMapper.toDTO(refund.getOrderModal()))
                .orderId(refund.getOrderModal().getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .shiftReportId(refund.getShiftReport().getId())
                .cashier(UserMapper.toDTO(refund.getCashier()))
                .cashierName(refund.getCashier().getFullName())
//                .branch(BranchMapper.to)
                .branchId(refund.getBranch().getId())
                .createdAt(refund.getCreatedAt())
                .paymentType(refund.getPaymentType())
                .build();

                return refundDTO;

    }
}
