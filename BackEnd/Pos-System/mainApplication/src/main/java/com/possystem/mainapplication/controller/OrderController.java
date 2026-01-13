package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.OrderService;
import com.possystem.mainapplication.domain.OrderStatus;
import com.possystem.mainapplication.domain.PaymentType;
import com.possystem.mainapplication.payload.DTO.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){

        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @GetMapping("/orderbyid/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id){
        return  ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/ordersbybranchid")
    public ResponseEntity<List<OrderDTO>> getByBranchId(@RequestParam("branchId") Long branchId, @RequestParam("customerId") Long customerId, @RequestParam("paymentType") PaymentType paymentType, @RequestParam("CashierId") Long CashierId, @RequestParam("orderStatus") OrderStatus orderStatus){
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId,customerId,CashierId,paymentType,orderStatus));
    }
    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCashier(@PathVariable("id") Long cashierId){
        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>  deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        HashMap<String, String> res=new HashMap<>();
        res.put("order id :",""+id);
        res.put("message : ","order  is deleted successfully..");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/todayordersbybranch/{id}")
    public ResponseEntity<List<OrderDTO>> getTodayOrdersByBranch(@PathVariable("id") Long branchId){
        return ResponseEntity.ok(orderService.getTodayOrderByBranch(branchId));
    }

    @GetMapping("/ordersbycustomerid/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable("id") Long custometId){
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(custometId));

    }
    @GetMapping("/top5ordersbybranch/{id}")
    public ResponseEntity<List<OrderDTO>> getTop5RecentOrdersByBranchId(@PathVariable("id") Long branchId){
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
    }







}
