package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.OrderModal;
import com.possystem.mainapplication.payload.DTO.OrderDTO;
import com.possystem.mainapplication.payload.DTO.OrderItemsDTO;

import java.util.stream.Collectors;

public class OrderMapper {
    public  static OrderDTO toDTO(OrderModal orderModal){
        OrderDTO orderDTO= OrderDTO.builder()
                .id(orderModal.getId())
                .totalAmount(orderModal.getTotalAmount())
                .createdAt(orderModal.getCreatedAt())
//                .branch(orderModal.getBranch())
                .branchId(orderModal.getBranch().getId())
                .customerId(orderModal.getCustomer().getId())
                .paymentType(orderModal.getPaymentType())
                .cashier(UserMapper.toDTO(orderModal.getCashier()))
                .customer(orderModal.getCustomer())
                .items(orderModal.getItems().stream().map(OrderItemsMapper::toDTO).collect(Collectors.toList()))
                .build();
        return orderDTO;

    }
}
