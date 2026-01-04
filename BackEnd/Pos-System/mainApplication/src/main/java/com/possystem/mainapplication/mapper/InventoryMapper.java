package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.*;
import com.possystem.mainapplication.payload.DTO.BranchDTO;
import com.possystem.mainapplication.payload.DTO.InventoryDTO;
import com.possystem.mainapplication.payload.DTO.ProductDTO;

public class InventoryMapper {
    public static InventoryDTO toDTO(InventoryModal inventoryModal, BranchDTO branchDTO) {
        InventoryDTO dto = InventoryDTO.builder().id(inventoryModal.getId()).branchId(inventoryModal.getBranch().getId()).branch(branchDTO != null ? branchDTO : null).productId(inventoryModal.getProduct().getId()).product(ProductMapper.toDTO(inventoryModal.getProduct())).quantity(inventoryModal.getQuantity()).lastUpdated(inventoryModal.getLastUpdated()).build();

        return dto;
    }

    public static InventoryModal toEntity(InventoryDTO inventoryDTO, BranchModal branchModal, ProductModal productModal) {
        InventoryModal modal = InventoryModal.builder().branch(branchModal).product(productModal).quantity(inventoryDTO.getQuantity()).lastUpdated(inventoryDTO.getLastUpdated()).build();

        return modal;
    }


}