package com.possystem.mainapplication.repository;


import com.possystem.mainapplication.modal.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<UserModal,Long> {

    UserModal findByEmail(String email);
}
