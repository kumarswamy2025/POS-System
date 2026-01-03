package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.BranchDTO;

import java.util.List;

public interface BranchService {

    BranchDTO createBranch(BranchDTO branchDTO, UserModal userModal);

    BranchDTO updateBranch(Long id, BranchDTO branchDTO, UserModal userModal);

    BranchDTO getBranchById(Long id);

    List<BranchDTO> getAllBranchesByStoreId(Long storeId);

    void deleteBranch(Long id);
}