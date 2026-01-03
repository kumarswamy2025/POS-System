package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.BranchModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepo extends JpaRepository<BranchModal,Long> {
    List<BranchModal> findByStoreId(Long storeId);
    
}
