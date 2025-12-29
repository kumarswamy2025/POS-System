package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.ProductService;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.ProductDTO;

import java.util.List;

public class ProductServiceImplementation implements ProductService {

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, UserModal userModal) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, UserModal userModal) {
        return null;
    }

    @Override
    public void deleteProduct(Long id, UserModal userModal) {

    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        return List.of();
    }

    @Override
    public ProductDTO getProductById(Long id, UserModal userModal) {
        return null;
    }
}
