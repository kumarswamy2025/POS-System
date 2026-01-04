package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.payload.DTO.InventoryDTO;

import java.util.List;

public interface InventoryService {

    InventoryDTO createInventory(InventoryDTO inventoryDTO);
    InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO);
    void deleteInventory(Long id);
    InventoryDTO getInventoryById(Long id);
    List<InventoryDTO> getInventoryByProductId(Long productId);
    List<InventoryDTO> getInventoryByBranchId(Long branchId);
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId,Long branchId);
    List<InventoryDTO> getAllInventories();



}
