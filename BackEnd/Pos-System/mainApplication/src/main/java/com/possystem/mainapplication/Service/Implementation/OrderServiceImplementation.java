package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.OrderService;
import com.possystem.mainapplication.domain.OrderStatus;
import com.possystem.mainapplication.domain.PaymentType;
import com.possystem.mainapplication.payload.DTO.OrderDTO;

import java.util.List;

public class OrderServiceImplementation  implements OrderService {
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByBranch(Long branchId, Long customerId, Long CashierId, PaymentType paymentType, OrderStatus orderStatus) {
        return List.of();
    }

    @Override
    public List<OrderDTO> getOrderByCashier(Long cashierId) {
        return List.of();
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public List<OrderDTO> getTodayOrderByBranch(Long branchId) {
        return List.of();
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return List.of();
    }

    @Override
    public List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) {
        return List.of();
    }
}
