package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.modal.RefundModal;
import com.possystem.mainapplication.payload.DTO.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {
    RefundDTO createRefund(RefundDTO refundModal);
    List<RefundDTO> getAllRefunds();
    List<RefundDTO> getRefundByCashier(Long cashierId);
    List<RefundDTO> getRefundByShiftReport(Long shiftReportId);
    List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate,LocalDateTime EndDate);

    List<RefundDTO> getRefundByBranch(Long branchId);
    RefundDTO getRefundById(Long refundID);
    void deleteRefund(Long refundId);




}
