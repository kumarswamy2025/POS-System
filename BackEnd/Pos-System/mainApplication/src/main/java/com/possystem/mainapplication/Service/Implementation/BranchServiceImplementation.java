package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.BranchService;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.BranchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImplementation implements BranchService {
    @Override
    public BranchDTO createBranch(BranchDTO branchDTO, UserModal userModal) {
        return null;
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO, UserModal userModal) {
        return null;
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        return null;
    }

    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public void deleteBranch(Long id) {

    }
}
