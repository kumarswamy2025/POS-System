package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.payload.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getCategoriesByStore(Long storeId);
    CategoryDTO updateCategory(Long id,CategoryDTO categoryDTO);
    void deleteCategory(Long id);

    void deleteCategoryByStoreId(Long id, Long StoreId);
}
