package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.ShiftReportModal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftReportRepo extends JpaRepository<ShiftReportModal,Long> {
}
