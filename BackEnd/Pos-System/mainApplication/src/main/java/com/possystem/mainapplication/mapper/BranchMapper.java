package com.possystem.mainapplication.mapper;

import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.BranchDTO;

public class BranchMapper {

    public static BranchDTO toDTO(BranchModal branchModal, StoreModal storeModal, UserModal userModal) {
        BranchDTO dto = BranchDTO.builder()
                .id(branchModal.getId())
                .name(branchModal.getName())
                .address(branchModal.getAddress())
                .phone(branchModal.getPhone())
                .email(branchModal.getEmail())
                .workingDays(branchModal.getWorkingDays())
                .openTime(branchModal.getOpenTime())
                .closeTime(branchModal.getCloseTime())
                .createdAt(branchModal.getCreatedAt())
                .updatedAt(branchModal.getUpdatedAt())
                .store(StoreMapper.toDTO(storeModal))
                .storeId(branchModal.getStore()!=null? branchModal.getStore().getId():null)
                .manager(UserMapper.toDTO(userModal))
                .managerId(userModal.getId())
                .build();
        return dto;

    }

    public static BranchModal toEntity(BranchDTO branchDTO, StoreModal storeModal, UserModal userModal) {
        BranchModal entity = BranchModal.builder()
                .name(branchDTO.getName())
                .address(branchDTO.getAddress())
                .phone(branchDTO.getPhone())
                .email(branchDTO.getEmail())
                .workingDays(branchDTO.getWorkingDays())
                .openTime(branchDTO.getOpenTime())
                .closeTime(branchDTO.getCloseTime())
//                .updatedAt(branchDTO.getUpdatedAt())
//                .createdAt(branchDTO.getCreatedAt())
//                .store(storeModal)
//                .manager(userModal)
                .build();

        return entity;
    }

}
