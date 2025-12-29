package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.StoreDTO;
import com.possystem.mainapplication.payload.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

public class StoreMapper {

    public static StoreDTO toDTO(StoreModal storeModal) {
        StoreDTO storeDTO = StoreDTO.builder()
                .id(storeModal.getId())
                .brand(storeModal.getBrand())
                .storeAdmin(UserMapper.toDTO(storeModal.getStoreAdmin()))
                .createdAt(storeModal.getCreatedAt())
                .updatedAt(storeModal.getUpdatedAt())
                .description(storeModal.getDescription())
                .storeType(storeModal.getStoreType())
                .status(storeModal.getStatus())
                .contact(storeModal.getContact())
                .build();

        return storeDTO;
    }

    public static StoreModal toEntity(StoreDTO storeDTO, UserModal userModal){
        StoreModal storeModal=StoreModal.builder()
//                here id is auto increment
                .id(storeDTO.getId())
                .brand(storeDTO.getBrand())
                .storeAdmin(userModal)
                .createdAt(storeDTO.getCreatedAt())
                .updatedAt(storeDTO.getUpdatedAt())
                .description(storeDTO.getDescription())
                .storeType(storeDTO.getStoreType())
                .status(storeDTO.getStatus())
                .contact(storeDTO.getContact())
                .build();



        return storeModal;


    }
}
