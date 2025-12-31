package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.CategoryModal;
import com.possystem.mainapplication.modal.ProductModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.payload.DTO.ProductDTO;

public class ProductMapper {
    //    modal to dto
    public static ProductDTO toDTO(ProductModal productModal) {
        ProductDTO dto = ProductDTO.builder()
                .id(productModal.getId())
                .name(productModal.getName())
                .sku(productModal.getSku())
                .description(productModal.getDescription())
                .mrp(productModal.getMrp())
                .sellingPrice(productModal.getSellingPrice())
                .brand(productModal.getBrand())
                .image(productModal.getImage())
                .storeId(productModal.getStore() != null ? productModal.getStore().getId() : null)
                .createdAt(productModal.getCreatedAt())
                .updatedAt(productModal.getUpdatedAt())
                .categoryId(productModal.getCategory().getId())
                .categoryDTO(CategoryMapper.toDTO(productModal.getCategory()))

                .build();
        return dto;
    }


    //    dto to modal
    public static ProductModal toEntity(ProductDTO productDTO, StoreModal storeModal, CategoryModal categoryModal) {
        ProductModal modal = ProductModal.builder()
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice(productDTO.getSellingPrice())
                .brand(productDTO.getBrand())
                .image(productDTO.getImage())
                .store(storeModal)
                .category(categoryModal)
                .build();
        return modal;
    }
}
