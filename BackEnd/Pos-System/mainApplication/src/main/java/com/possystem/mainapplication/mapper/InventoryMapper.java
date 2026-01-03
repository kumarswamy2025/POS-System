package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.CategoryModal;
import com.possystem.mainapplication.modal.InventoryModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.payload.DTO.BranchDTO;
import com.possystem.mainapplication.payload.DTO.InventoryDTO;
import com.possystem.mainapplication.payload.DTO.ProductDTO;

public class InventoryMapper {
    public static InventoryDTO toDTO(InventoryModal inventoryModal, BranchDTO branchDTO){
        InventoryDTO dto=InventoryDTO.builder()
                .id(inventoryModal.getId())
                .branchId(inventoryModal.getBranch().getId())
                .branch(branchDTO)
                .productId(inventoryModal.getProduct().getId())
                .product(ProductMapper.toDTO(inventoryModal.getProduct()))
                .quantity(inventoryModal.getQuantity())
                .lastUpdated(inventoryModal.getLastUpdated())
                .build();

        return dto;
    }

    public static  InventoryModal toEntity(InventoryDTO inventoryDTO, StoreModal storeModal, CategoryModal categoryModal){
        InventoryModal modal=InventoryModal.builder()
                .branch(BranchMapper.toEntity(inventoryDTO.getBranch(),null,null))
                .product(ProductMapper.toEntity(inventoryDTO.getProduct(),storeModal,categoryModal))
                .quantity(inventoryDTO.getQuantity())
                .lastUpdated(inventoryDTO.getLastUpdated())
                .build();

        return modal;
    }
}
