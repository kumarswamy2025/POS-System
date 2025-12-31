package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.CategoryService;
import com.possystem.mainapplication.payload.DTO.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    //    creating category
    @PostMapping("/create")
   public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

//    get categories by store id
    @GetMapping("/storeid/{id}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByStoreId(@PathVariable("id") Long storeId) {

        return ResponseEntity.ok(categoryService.getCategoriesByStore(storeId));

    }


//    used to update category
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") long categoryId,@RequestBody  CategoryDTO categoryDTO){

                return ResponseEntity.ok(categoryService.updateCategory(categoryId,categoryDTO));
    }

//    used to delete category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){

        categoryService.deleteCategory(id);
        HashMap<String, String> res=new HashMap<>();
        res.put("Category id :",""+id);
        res.put("message : ","Category  is deleted successfully..");
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deletebystoreid/{categoryid}/{storeid}")
    public  ResponseEntity<?> deleteCategoryByStoreId(@PathVariable("categoryid") Long categoryId,@PathVariable("storeid") Long storeid ){
        categoryService.deleteCategoryByStoreId(categoryId,storeid);
        HashMap<String, String> res=new HashMap<>();
        res.put("Category id :",""+categoryId);
        res.put("message : ","Category  is deleted successfully..");
        return ResponseEntity.ok(res);
    }




}
