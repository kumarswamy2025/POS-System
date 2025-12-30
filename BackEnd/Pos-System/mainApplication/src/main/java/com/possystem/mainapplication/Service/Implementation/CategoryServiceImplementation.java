package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.CategoryService;
import com.possystem.mainapplication.payload.DTO.CategoryDTO;

import java.util.List;

public class CategoryServiceImplementation implements CategoryService {
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {
        return List.of();
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
