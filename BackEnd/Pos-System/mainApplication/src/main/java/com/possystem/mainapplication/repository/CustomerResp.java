package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.CustomerModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerResp  extends JpaRepository<CustomerModal,Long> {

    List<CustomerModal> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String fullname,String email);

}
