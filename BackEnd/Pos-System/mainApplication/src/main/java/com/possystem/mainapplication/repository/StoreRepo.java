package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.StoreModal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<StoreModal,Long> {
    StoreModal findByAdminId(Long adminId);

    StoreModal findByStoreAdminId(Long id);
}
