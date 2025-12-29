package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO, UserModal userModal);

    ProductDTO updateProduct(Long id, ProductDTO productDTO, UserModal userModal);

    void deleteProduct(Long id, UserModal userModal);

    List<ProductDTO> getProductsByStoreId(Long storeId);

    List<ProductDTO> searchByKeyword(Long storeId, String keyword);

    ProductDTO getProductById(Long id, UserModal userModal);

}
