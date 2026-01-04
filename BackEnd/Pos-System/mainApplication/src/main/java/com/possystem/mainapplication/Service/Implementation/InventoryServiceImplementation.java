package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.InventoryService;
import com.possystem.mainapplication.payload.DTO.InventoryDTO;

import java.util.List;

public class InventoryServiceImplementation  implements InventoryService {
    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        return null;
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
        return null;
    }

    @Override
    public void deleteInventory(Long id) {

    }

    @Override
    public InventoryDTO getInventory(Long id) {
        return null;
    }

    @Override
    public List<InventoryDTO> getInventoryByProductId(Long productId) {
        return List.of();
    }

    @Override
    public List<InventoryDTO> getInventoryByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        return null;
    }

    @Override
    public List<InventoryDTO> getAllInventories() {
        return List.of();
    }
}
