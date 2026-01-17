package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.*;
import com.possystem.mainapplication.exceptions.RefundException.RefundException;
import com.possystem.mainapplication.mapper.RefundMapper;
import com.possystem.mainapplication.mapper.UserMapper;
import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.OrderModal;
import com.possystem.mainapplication.modal.RefundModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.RefundDTO;
import com.possystem.mainapplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImplementation implements RefundService {
    //    here we inject repos
    private final OrderRepo orderRepo;
    private final ShiftReportRepo shiftReportRepo;
    private final  UserRepo userRepo;
    private final BranchRepo branchRepo;
    //    here we inject services
    private final OrderService orderService;
    private final ShiftReportService shiftReportService;
    private final UserService userService;
    private final BranchService branchService;
    private final RefundRepo refundRepo;

    @Override
    public RefundDTO createRefund(RefundDTO refund) {

//        chek if order is exists or not
        OrderModal orderModal=orderRepo.findById(refund.getOrderModal().getId()).orElseThrow(
                ()-> new RefundException("order is not found...", HttpStatus.NOT_FOUND)
        );

//   current user is cashier
        UserModal cashier=userService.getCurrentUser();
        BranchModal branch=cashier.getBranch();

//        creating refund modal
        RefundModal refundModal=RefundModal.builder()
                .orderModal(orderModal)
                .reason(refund.getReason())
                .amount(refund.getAmount())
//                .shiftReport()
                .cashier(cashier)
                .branch(branch)
                .createdAt(refund.getCreatedAt())
                .paymentType(refund.getPaymentType())
                .build();

//        saved data
        RefundModal savedData=refundRepo.save(refundModal);

        RefundDTO refundDTO= RefundMapper.toDTO(savedData);
        return refundDTO;
    }

    @Override
    public List<RefundDTO> getAllRefunds() {
        List<RefundModal> refundModals=refundRepo.findAll();
        return  refundModals.stream().map(RefundMapper::toDTO).collect(Collectors.toList());


    }

    @Override
    public List<RefundDTO> getRefundByCashier(Long cashierId) {

//        checkn if cashier is exits or not
        UserModal cashier=userRepo.findById(cashierId).orElseThrow(
                ()-> new RefundException("cashier is not found...",HttpStatus.NOT_FOUND)
        );

     return refundRepo.findByCashierId(cashierId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());




    }

    @Override
    public  List<RefundDTO> getRefundByShiftReport(Long shiftReportId) {

        return  refundRepo.findByShirtReportId(shiftReportId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());


    }

    @Override
    public List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime EndDate) {

        return refundRepo.findByCashierIdAndCreatedAtBetween(cashierId,startDate,EndDate).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) {
//        check if branch is exits or not
        BranchModal branchModal=branchRepo.findById(branchId).orElseThrow(
                ()-> new RefundException("branch is not found...",HttpStatus.NOT_FOUND)
        );

        return  refundRepo.findByBranchId(branchId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RefundDTO getRefundById(Long refundID) {
//  checking if refund is exists or not
        RefundModal refundModal=refundRepo.findById(refundID).orElseThrow(
                ()-> new RefundException("refund is not found please check...",HttpStatus.NOT_FOUND)
        );

        return RefundMapper.toDTO(refundModal);
    }

    @Override
    public void deleteRefund(Long refundId) {
        //  checking if refund is exists or not
        RefundModal refundModal=refundRepo.findById(refundId).orElseThrow(
                ()-> new RefundException("refund is not found please check...",HttpStatus.NOT_FOUND)
        );

        refundRepo.delete(refundModal);


    }
}
