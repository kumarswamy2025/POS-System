package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.OrderModal;
import com.possystem.mainapplication.modal.ProductModal;
import com.possystem.mainapplication.modal.RefundModal;
import com.possystem.mainapplication.modal.ShiftReportModal;
import com.possystem.mainapplication.payload.DTO.OrderDTO;
import com.possystem.mainapplication.payload.DTO.ProductDTO;
import com.possystem.mainapplication.payload.DTO.RefundDTO;
import com.possystem.mainapplication.payload.DTO.ShiftReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {
    public  static ShiftReportDTO toDTO(ShiftReportModal shiftReport){
        ShiftReportDTO dto=ShiftReportDTO.builder()
                .id(shiftReport.getId())
                .shiftStart(shiftReport.getShiftStart())
                .shiftEnd(shiftReport.getShiftEnd())
                .totalSale(shiftReport.getTotalSale())
                .totalRefund(shiftReport.getTotalRefund())
                .netSale(shiftReport.getNetSale())
                .totalOrders(shiftReport.getTotalOrders())
                .cashier(UserMapper.toDTO(shiftReport.getCashier()))
                .cashierId(shiftReport.getCashier().getId())
                .branch(BranchMapper.toDTO(shiftReport.getBranch(),null,null))
                .branchId(shiftReport.getBranch().getId())
                .paymentSummaries(shiftReport.getPaymentSummaries())
                .topSellingProducts(mapProducts(shiftReport.getTopSellingProducts()))
                .recentOrders(mapOrders(shiftReport.getRecentOrders()))
                .refunds(mapRefunds(shiftReport.getRefunds()))
                .build();
        return dto;
    }

    private static List<RefundDTO> mapRefunds(List<RefundModal> refunds) {

        if(refunds==null || refunds.isEmpty()) return null;
        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    private static List<OrderDTO> mapOrders(List<OrderModal> recentOrders) {

        if(recentOrders==null || recentOrders.isEmpty()) return null;
        return recentOrders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<ProductModal> topSellingProducts) {

        if(topSellingProducts==null || topSellingProducts.isEmpty()) return null;
        return topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}
