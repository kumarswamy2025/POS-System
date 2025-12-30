package com.possystem.mainapplication.repository;


import com.possystem.mainapplication.modal.CategoryModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryModal,Long> {
    List<CategoryModal> findByStoreId(Long storeId);

    @Query("select c from CategoryModal c where c.id=:id and c.store.id=:storeId ")
    CategoryModal findCategoryByIdAndStoreId(@Param("id") Long id,@Param("storeId") Long storeId);


}
