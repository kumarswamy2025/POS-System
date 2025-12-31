package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.CategoryModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.payload.DTO.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO toDTO(CategoryModal categoryModal){

        return CategoryDTO.builder()
                .id(categoryModal.getId())
                .name(categoryModal.getName())
                .createdAt(categoryModal.getCreatedAt())
                .updatedAt(categoryModal.getUpdatedAt())
                .storeId(categoryModal.getStore()!=null?categoryModal.getStore().getId():null)
                .build();
    }

    public static CategoryModal toEntity(CategoryDTO categoryDTO, StoreModal storeModal){
        return CategoryModal.builder()
                .name(categoryDTO.getName())
                .store(storeModal)
                .build();
    }
}
