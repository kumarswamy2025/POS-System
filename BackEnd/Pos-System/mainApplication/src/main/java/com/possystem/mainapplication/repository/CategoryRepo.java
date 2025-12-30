package com.possystem.mainapplication.repository;


import com.possystem.mainapplication.modal.CategoryModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryModal,Long> {

}
