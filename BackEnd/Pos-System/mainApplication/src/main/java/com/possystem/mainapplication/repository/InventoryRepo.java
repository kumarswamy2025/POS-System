package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.InventoryModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepo extends JpaRepository<InventoryModal ,Long> {
    List<InventoryModal> findByProductId(Long productId);
    List<InventoryModal> findByBranchId(Long branchId);
    InventoryModal findByProductIdAndBranchId(Long productId,Long branchId);

 }
