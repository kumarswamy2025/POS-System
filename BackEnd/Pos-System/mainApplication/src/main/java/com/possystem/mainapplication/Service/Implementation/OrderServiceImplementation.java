package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.OrderService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.domain.OrderStatus;
import com.possystem.mainapplication.domain.PaymentType;
import com.possystem.mainapplication.exceptions.CustomerException.CustomerException;
import com.possystem.mainapplication.exceptions.OrderException.OrderException;
import com.possystem.mainapplication.exceptions.OrderItemsException.OrderItemsException;
import com.possystem.mainapplication.mapper.OrderMapper;
import com.possystem.mainapplication.modal.*;
import com.possystem.mainapplication.payload.DTO.OrderDTO;
import com.possystem.mainapplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    //    here we inject repos
    private final ProductRepo productRepo;
    private final BranchRepo branchRepo;
    private final CategoryRepo categoryRepo;
    private final StoreRepo storeRepo;
    private final UserRepo userRepo;

    //    here we inject services
    private final UserService userService;
    private final OrderRepo orderRepo;
    private final CustomerResp customerResp;


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
//        getting  current user data
        UserModal cashier = userService.getCurrentUser();
//        getting currentb user branch
        BranchModal branchData = cashier.getBranch();
        if (branchData == null) {
            throw new OrderException("cashier's branch not found...", HttpStatus.NOT_FOUND);
        }

//       now create order
        OrderModal orderModal = OrderModal.builder()
                .branch(branchData)
                .cashier(cashier)
                .customer(orderDTO.getCustomer())
                .paymentType(orderDTO.getPaymentType())
                .build();
// order items list
//        checking if order items are present or not
        List<OrderItemsModal> orderItems = orderDTO.getItems().stream().map(

                itemDto -> {
                    ProductModal productModal = productRepo.findById(itemDto.getProductId()).orElseThrow(
                            () -> new OrderItemsException("product is not found...", HttpStatus.NOT_FOUND)
                    );
                    return OrderItemsModal.builder()
                            .product(productModal)
                            .quantity(itemDto.getQuantity())
                            .price(productModal.getSellingPrice() * itemDto.getQuantity())
                            .order(orderModal)
                            .build();

                }
        ).toList();

//        now set order items in order
        double totalAmount=orderItems.stream().mapToDouble(
                OrderItemsModal::getPrice
        ).sum();
        orderModal.setTotalAmount(totalAmount);
        orderModal.setItems(orderItems);


//        save data to DB
        OrderModal savedData=orderRepo.save(orderModal);


        return OrderMapper.toDTO(savedData);
    }

    @Override
    public OrderDTO getOrderById(Long id) {

//        checking if order is exits or not
        OrderModal orderModal=orderRepo.findById(id).orElseThrow(
                ()-> new OrderException("order not found..",HttpStatus.NOT_FOUND)
        );
        return   OrderMapper.toDTO(orderModal);

    }

    @Override
    public List<OrderDTO> getOrdersByBranch(Long branchId, Long customerId, Long CashierId, PaymentType paymentType, OrderStatus orderStatus) {

      return  orderRepo.findByBranchId(branchId).stream()
              .filter(order-> customerId==null || (order.getCustomer()!=null && order.getCustomer().getId().equals(customerId)))
              .filter(order ->  CashierId==null || (order.getCashier()!=null && order.getCashier().getId().equals(CashierId)))
              .filter(order -> paymentType==null || order.getPaymentType()==paymentType)
              .map(OrderMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<OrderDTO> getOrderByCashier(Long cashierId) {
//        checking if cashier is exits are not
        return orderRepo.findByCashierId(cashierId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());


    }

    @Override
    public void deleteOrder(Long id) {

//        check if id is exits or not
        OrderModal orderModal=orderRepo.findById(id).orElseThrow(
                ()-> new OrderException("order not found,..",HttpStatus.NOT_FOUND)
        );

        orderRepo.delete(orderModal);



    }

    @Override
    public List<OrderDTO> getTodayOrderByBranch(Long branchId) {

        LocalDate today=LocalDate.now();
        LocalDateTime start=today.atStartOfDay();
        LocalDateTime end=today.plusDays(1).atStartOfDay();

        return orderRepo.findByBranchIdAndCreatedAtBetween(branchId,start,end).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
//        checking id customer is exists or not

        CustomerModal modal=customerResp.findById(customerId).orElseThrow(
                ()-> new CustomerException("customer is not found..",HttpStatus.NOT_FOUND)
        );

        return orderRepo.findByCashierId(customerId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());


    }

    @Override
    public List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) {
        return orderRepo.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());

    }
}
