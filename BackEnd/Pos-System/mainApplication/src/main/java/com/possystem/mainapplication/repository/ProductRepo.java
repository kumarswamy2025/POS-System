package com.possystem.mainapplication.repository;

import com.possystem.mainapplication.modal.ProductModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<ProductModal, Long> {

    //    this is used to find store by id
    List<ProductModal> findByStoreId(Long storeId);

//    this is used to find products by name , brand and sku
    @Query("select p from ProductModal p " +
            "where  p.store.id=:storeId and (" +
                    "lower(p.name) like  lower(concat('%',:query,'%') ) " +
                    " or lower(p.brand) like  lower(concat('%',:query,'%') ) " +
                    " or lower(p.sku) like  lower(concat('%',:query,'%') ) "+")")
    List<ProductModal> searchByKeyword(@Param("storeId") Long storeId, @Param("query") String keyword);


//    in this query we find product and with store id
    @Query(
            "select p from ProductModal p where p.id=:id and p.store.id=:storeId"
    )
    ProductModal findByIdAndByStoreId(@Param("id") Long id,@Param("storeId") Long storeId);
}
