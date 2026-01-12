package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.OrderItemsModal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepo extends JpaRepository<OrderItemsModal,Long> {
}
