package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.StoreModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepo extends JpaRepository<StoreModal,Long> {
//    StoreModal findByAdminId(Long adminId);

    StoreModal findByStoreAdminId(Long id);
    @Query("select s from StoreModal  s where s.id=:id")
    StoreModal findByStoreIdKumar(@Param("id") Long id);
}
