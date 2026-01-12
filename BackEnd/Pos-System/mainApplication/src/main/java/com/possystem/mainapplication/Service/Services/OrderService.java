package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.domain.OrderStatus;
import com.possystem.mainapplication.domain.PaymentType;
import com.possystem.mainapplication.payload.DTO.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersByBranch(Long branchId, Long customerId, Long CashierId, PaymentType paymentType, OrderStatus orderStatus);
    List<OrderDTO> getOrderByCashier(Long cashierId);
    void deleteOrder(Long id );
    List<OrderDTO> getTodayOrderByBranch(Long branchId);
    List<OrderDTO> getOrdersByCustomerId(Long customerId);
    List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId);;

}
