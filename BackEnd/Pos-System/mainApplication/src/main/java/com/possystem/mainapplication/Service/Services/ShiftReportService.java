package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.payload.DTO.ShiftReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime startTime);

    ShiftReportDTO endShift(Long shiftReportId,LocalDateTime shiftEnd);

    ShiftReportDTO getShiftReportById(Long id);

    List<ShiftReportDTO> getAllShiftReports();

    List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId);

    List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId);

    ShiftReportDTO getCurrentShiftProgress(Long cashierId);

    ShiftReportDTO getShiftByCashierAndDate(Long cashierId,LocalDateTime date);



}
