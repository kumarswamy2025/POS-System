package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.OrderItemsModal;
import com.possystem.mainapplication.payload.DTO.OrderDTO;
import com.possystem.mainapplication.payload.DTO.OrderItemsDTO;

import java.util.List;

public class OrderItemsMapper {
    public static OrderItemsDTO toDTO(OrderItemsModal orderItemsModal){
        if(orderItemsModal==null){
            return null;
        }
        OrderItemsDTO build= OrderItemsDTO.builder()
                .id(orderItemsModal.getId())
                .quantity(orderItemsModal.getQuantity())
                .price(orderItemsModal.getPrice())
                .product(ProductMapper.toDTO(orderItemsModal.getProduct()))
                .productId(orderItemsModal.getProduct().getId())
                .orderId(orderItemsModal.getOrder().getId())
                .build();
        return build;
    }
}
