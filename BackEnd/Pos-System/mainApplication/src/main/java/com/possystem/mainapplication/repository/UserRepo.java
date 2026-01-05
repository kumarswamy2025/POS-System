package com.possystem.mainapplication.repository;


import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo  extends JpaRepository<UserModal,Long> {

    UserModal findByEmail(String email);

    List<UserModal> findByStore(StoreModal storeModal);
    List<UserModal> findByBranchId(Long branchId);


}
