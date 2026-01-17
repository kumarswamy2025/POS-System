package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.RefundModal;
import com.possystem.mainapplication.modal.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepo extends JpaRepository<RefundModal,Long> {

    List<RefundModal> findByCashierIdAndCreatedAtBetween(Long cashier, LocalDateTime from,LocalDateTime to);

    List<RefundModal> findByCashierId(Long id);
    List<RefundModal> findByShirtReportId(Long id);
    List<RefundModal> findByBranchId(Long id);


}
