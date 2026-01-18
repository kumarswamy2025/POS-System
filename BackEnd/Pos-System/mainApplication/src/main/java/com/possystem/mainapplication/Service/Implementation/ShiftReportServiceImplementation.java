package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.ShiftReportService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.domain.PaymentType;
import com.possystem.mainapplication.exceptions.ShiftReportException.ShiftReportException;
import com.possystem.mainapplication.mapper.BranchMapper;
import com.possystem.mainapplication.mapper.ShiftReportMapper;
import com.possystem.mainapplication.modal.*;
import com.possystem.mainapplication.payload.DTO.ShiftReportDTO;
import com.possystem.mainapplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImplementation implements ShiftReportService {
    //    here we inject repos

    private final ShiftReportRepo shiftReportRepo;


    //    here we inject services

    private final UserService userService;
    private final BranchRepo branchRepo;
    private final RefundRepo refundRepo;
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;


    @Override
    public ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) {

        UserModal currentUser = userService.getCurrentUser();
        shiftStart = LocalDateTime.now();

        LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);

        LocalDateTime EndOfDay = shiftStart.withHour(23).withMinute(59).withSecond(59);

        Optional<ShiftReportModal> existing = shiftReportRepo.findByCashierAndShiftStartBetween(currentUser, startOfDay, EndOfDay);

        if (existing.isPresent()) {
            throw new ShiftReportException("shift already started today", HttpStatus.ACCEPTED);
        }

        BranchModal branchModal = currentUser.getBranch();

        ShiftReportModal shiftReportModal = ShiftReportModal.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .build();

        ShiftReportModal savedData = shiftReportRepo.save(shiftReportModal);

        return ShiftReportMapper.toDTO(shiftReportModal);
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) {

        UserModal currentUser = userService.getCurrentUser();

        ShiftReportModal shiftReportModal = shiftReportRepo.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser).orElseThrow(
                () -> new ShiftReportException("shift not found", HttpStatus.NOT_FOUND)
        );

        shiftReportModal.setShiftEnd(shiftEnd);

        List<RefundModal> refunds = refundRepo.findByCashierIdAndCreatedAtBetween(currentUser.getId(), shiftReportModal.getShiftStart(), shiftReportModal.getShiftEnd());

        double totalRefunds = refunds.stream().mapToDouble(refund -> refund.getAmount() != null ? refund.getAmount() : 0.0).sum();

        List<OrderModal> orders = orderRepo.findByCashierAndCreatedAtBetween(currentUser.getId(), shiftReportModal.getShiftStart(), shiftReportModal.getShiftEnd());

        double totalSales = orders.stream().mapToDouble(OrderModal::getTotalAmount).sum();

        int totalOrders = orders.size();
        double netSales = totalSales - totalRefunds;
        shiftReportModal.setTotalRefund(totalRefunds);
        shiftReportModal.setTotalSale(totalSales);
        shiftReportModal.setTotalOrders(totalOrders);
        shiftReportModal.setNetSale(netSales);
        shiftReportModal.setRecentOrders(getRecentOrders(orders));
        shiftReportModal.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReportModal.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReportModal.setRefunds(refunds);

        ShiftReportModal savedData = shiftReportRepo.save(shiftReportModal);

        return ShiftReportMapper.toDTO(savedData);

    }


    @Override
    public ShiftReportDTO getShiftReportById(Long id) {

//        checking if shift report is exists or not

        ShiftReportModal shiftReportModal = shiftReportRepo.findById(id).orElseThrow(
                () -> new ShiftReportException("report is not found...", HttpStatus.NOT_FOUND)
        );

        return ShiftReportMapper.toDTO(shiftReportModal);
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
       List< ShiftReportModal> shiftReportModal=shiftReportRepo.findAll();
      return shiftReportModal.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());


    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) {

//        checkiong if branch is exists
        BranchModal branchModal=branchRepo.findById(branchId).orElseThrow(
                ()-> new ShiftReportException("Branch is not found...",HttpStatus.NOT_FOUND)
        );

        List<ShiftReportModal> shiftReportModal=shiftReportRepo.findByBranchId(branchId);

       return shiftReportModal.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());


    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) {
//   checking if cashier is exists or not

        UserModal cashier= userRepo.findById(cashierId).orElseThrow(
                ()-> new ShiftReportException("cashier is not found..",HttpStatus.NOT_FOUND)
        );

        List<ShiftReportModal> shiftReportModals=shiftReportRepo.findByCashierId(cashierId);
        return   shiftReportModals.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());


    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress(Long cashierId) {

        UserModal userModal=userService.getCurrentUser();
        ShiftReportModal shiftReportModal=shiftReportRepo.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(userModal).orElseThrow(
                ()-> new ShiftReportException("no active shift found for cashier..",HttpStatus.NOT_FOUND)
        );
        LocalDateTime now=LocalDateTime.now();

        List<OrderModal> orders=orderRepo.findByCashierAndCreatedAtBetween(userModal.getId(),shiftReportModal.getShiftStart(),now);

//        copied here

        List<RefundModal> refunds = refundRepo.findByCashierIdAndCreatedAtBetween(userModal.getId(), shiftReportModal.getShiftStart(), shiftReportModal.getShiftEnd());

        double totalRefunds = refunds.stream().mapToDouble(refund -> refund.getAmount() != null ? refund.getAmount() : 0.0).sum();



        double totalSales = orders.stream().mapToDouble(OrderModal::getTotalAmount).sum();

        int totalOrders = orders.size();
        double netSales = totalSales - totalRefunds;
        shiftReportModal.setTotalRefund(totalRefunds);
        shiftReportModal.setTotalSale(totalSales);
        shiftReportModal.setTotalOrders(totalOrders);
        shiftReportModal.setNetSale(netSales);
        shiftReportModal.setRecentOrders(getRecentOrders(orders));
        shiftReportModal.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReportModal.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReportModal.setRefunds(refunds);

        ShiftReportModal savedData = shiftReportRepo.save(shiftReportModal);

        return ShiftReportMapper.toDTO(savedData);

    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) {
//        checking if cashier is exists or not
        UserModal userModal=userRepo.findById(cashierId).orElseThrow(
                ()-> new ShiftReportException("cashier not found..",HttpStatus.NOT_FOUND)
        );

        LocalDateTime start=date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end=date.withHour(23).withMinute(59).withSecond(59);

        ShiftReportModal shiftReportModal=shiftReportRepo.findByCashierAndShiftStartBetween(userModal,start,end).orElseThrow(
                ()-> new ShiftReportException("shift report not found with given id ",HttpStatus.NOT_FOUND)
        );


        return ShiftReportMapper.toDTO(shiftReportModal);
    }


//    ------------------------------------- Helper Methods ----------------------------------------------

    private List<PaymentSummary> getPaymentSummaries(List<OrderModal> orders, double totalSales) {

        Map<PaymentType, List<OrderModal>> grouped = orders.stream().collect(Collectors.groupingBy(order -> order.getPaymentType() != null ? order.getPaymentType() : PaymentType.CASH));
        List<PaymentSummary> summaries = new ArrayList<>();
        for (Map.Entry<PaymentType, List<OrderModal>> entry : grouped.entrySet()) {
            double amount = entry.getValue().stream().mapToDouble(OrderModal::getTotalAmount).sum();

            int transactions = entry.getValue().size();
            double percentage = (amount / totalSales) * 100;

            PaymentSummary ps = new PaymentSummary();

            ps.setType(entry.getKey());
            ps.setTotalAmount(amount);
            ps.setTransactionCount(transactions);
            ps.setPercentage(percentage);
            summaries.add(ps);


        }
        return summaries;


    }

    private List<ProductModal> getTopSellingProducts(List<OrderModal> orders) {

        Map<ProductModal, Integer> productSalesMap = new HashMap<>();

        for (OrderModal order : orders) {
            for (OrderItemsModal item : order.getItems()) {
                ProductModal productModal = item.getProduct();
                productSalesMap.put(productModal, productSalesMap.getOrDefault(productModal, 0) + item.getQuantity());

            }
        }

        return productSalesMap.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue())).limit(5).map(Map.Entry::getKey).collect(Collectors.toList());

    }

    private List<OrderModal> getRecentOrders(List<OrderModal> orders) {

        return orders.stream().sorted(Comparator.comparing(OrderModal::getCreatedAt).reversed()).limit(5).collect(Collectors.toList());
    }


}
