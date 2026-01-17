package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.ShiftReportService;
import com.possystem.mainapplication.payload.DTO.ShiftReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImplementation implements ShiftReportService {
    @Override
    public ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime startTime) {
        return null;
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftReportById(Long id) {
        return null;
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress(Long cashierId) {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) {
        return null;
    }
}
