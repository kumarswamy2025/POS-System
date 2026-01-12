package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.OrderModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo extends JpaRepository<OrderModal, Long> {

    List<OrderModal> findByCustomerId(Long customerId);

    List<OrderModal> findByBranchId(Long branchId);

    List<OrderModal> findByCashierId(Long cashierId);

    List<OrderModal> findByBranchIdAndCreatedAtBetween(Long branchId, LocalDateTime from, LocalDateTime to);

    List<OrderModal> findByCashierAndCreatedAtBetween(Long cashierId, LocalDateTime from, LocalDateTime to);

    List<OrderModal> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);

}
