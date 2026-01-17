package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.ShiftReportModal;
import com.possystem.mainapplication.modal.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepo extends JpaRepository<ShiftReportModal,Long> {

    List<ShiftReportModal> findByCashierId(Long cashierId);

    List<ShiftReportModal> findByBranchId(Long id);


    Optional<ShiftReportModal> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(UserModal cashier);


    Optional<ShiftReportModal> findByCashierAndShiftStartBetween(UserModal cashier, LocalDateTime start,LocalDateTime end);


}
