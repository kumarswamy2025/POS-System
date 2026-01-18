package com.possystem.mainapplication.controller;


import com.possystem.mainapplication.Service.Services.ShiftReportService;
import com.possystem.mainapplication.payload.DTO.ShiftReportDTO;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-reports")
public class ShiftReportController {
    private final ShiftReportService shiftReportService;

    @PostMapping("/start")
    public ResponseEntity<ShiftReportDTO> startShift() {

        return ResponseEntity.ok(shiftReportService.startShift());
    }

    @PostMapping("/end/{id}")
    public ResponseEntity<ShiftReportDTO> endShift(@PathVariable("id") Long shiftReportId, @RequestHeader LocalDateTime shiftEnd) {

        return ResponseEntity.ok(shiftReportService.endShift(shiftReportId, shiftEnd));
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<ShiftReportDTO> getReportById(@PathVariable("id") Long reportId) {
        return ResponseEntity.ok(shiftReportService.getShiftReportById(reportId));
    }


    @GetMapping("/allshiftreports")
    public ResponseEntity<List<ShiftReportDTO>> getAllReports() {
        return ResponseEntity.ok(shiftReportService.getAllShiftReports());
    }

    @GetMapping("/reportsnybranchid/{id}")
    public ResponseEntity<List<ShiftReportDTO>> shiftReportByBranchId(@PathVariable("id") Long branhId){

        return ResponseEntity.ok(shiftReportService.getShiftReportsByBranchId(branhId));
    }


    @GetMapping("/reportsByCashierId/{id}")
    public ResponseEntity<List<ShiftReportDTO>> getShiftReportsByCashierId(@PathVariable("id") Long cashierID){

        return  ResponseEntity.ok(shiftReportService.getShiftReportsByCashierId(cashierID));
    }

    @GetMapping("/shiftprograss/{id}")
    public ResponseEntity<ShiftReportDTO> getCurrentShiftProgress(@PathVariable("id") Long  cashierId){
        return ResponseEntity.ok(shiftReportService.getCurrentShiftProgress(cashierId));

    }

    @GetMapping("/shiftbycashieranddate/{id}")
    public ResponseEntity<ShiftReportDTO>    getShiftByCashierAndDate(@PathVariable("id") Long cashierId,@RequestHeader LocalDateTime date){

        return ResponseEntity.ok(shiftReportService.getShiftByCashierAndDate(cashierId,date));
    }





}
