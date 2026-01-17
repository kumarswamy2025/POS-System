package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.RefundService;
import com.possystem.mainapplication.payload.DTO.RefundDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refunds")
public class RefundController {
    private final RefundService refundService;

    @PostMapping("/create")
    public ResponseEntity<RefundDTO> create(@RequestBody RefundDTO refundDTO) {
        return ResponseEntity.ok(refundService.createRefund(refundDTO));
    }

    @GetMapping("/allrefunds")
    public ResponseEntity<List<RefundDTO>> getAllRefunds() {
        return ResponseEntity.ok(refundService.getAllRefunds());

    }

    @GetMapping("/refundByCashier/{id}")
    public ResponseEntity<List<RefundDTO>> getRefundByCashier(@PathVariable("id") Long cashierId) {
        return ResponseEntity.ok(refundService.getRefundByCashier(cashierId));
    }

    @GetMapping("/refundByshiftreport/{id}")
    public ResponseEntity<List<RefundDTO>> getRefundByShiftReport(@PathVariable("id") Long shiftReportId) {
        return ResponseEntity.ok(refundService.getRefundByShiftReport(shiftReportId));
    }


    @GetMapping("/refundByCashieranddaterange/{id}/range")
    public ResponseEntity<List<RefundDTO>> getRefundByCashierAndDateRange(@PathVariable("id") Long cashierId, @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startdate, @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime enddate) {


        return ResponseEntity.ok(refundService.getRefundByCashierAndDateRange(cashierId,startdate,enddate));
    }

    @GetMapping("/refundByBranch/{id}")
    public ResponseEntity<List<RefundDTO>> getRefundByBranch(@PathVariable("id") Long branchId) {
        return ResponseEntity.ok(refundService.getRefundByBranch(branchId));
    }

    @GetMapping("/refundById/{id}")
    public ResponseEntity<RefundDTO> getById(@PathVariable("id") Long refundId) {
        return ResponseEntity.ok(refundService.getRefundById(refundId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRefund(@PathVariable("id") Long deleteId) {
        refundService.deleteRefund(deleteId);
        HashMap<String, String> res = new HashMap<>();
        res.put("Refund id :", "" + deleteId);
        res.put("message : ", "Refund is deleted successfully..");
        return ResponseEntity.ok(res);

    }


}
