package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.ProductService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

//    create product
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String jwt){
//        here we extract user data from jwt
        UserModal userModal=userService.getUserFromJWTToken(jwt);
     return   ResponseEntity.ok(productService.createProduct(productDTO,userModal));
    }

//    update product
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO, @RequestHeader("Authorization") String jwt, @PathVariable("id") Long productId){
        //        here we extract user data from jwt
        UserModal userModal=userService.getUserFromJWTToken(jwt);
        return ResponseEntity.ok(productService.updateProduct(productId,productDTO,userModal));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id,@RequestHeader("Authorization") String jwt){
        //        here we extract user data from jwt
        UserModal userModal=userService.getUserFromJWTToken(jwt);

        productService.deleteProduct(id,userModal);
        HashMap<String, String> res=new HashMap<>();
        res.put("product id :",""+id);
        res.put("message : ","product is deleted successfully..");



        return ResponseEntity.ok(res);
    }


    @GetMapping("/storeid/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByStoreId(@PathVariable("id") Long storeId){
        return ResponseEntity.ok(productService.getProductsByStoreId(storeId));
    }


    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDTO>> searchByKeyword(@RequestBody String keyword,@PathVariable("storeId") Long storeId){

        return ResponseEntity.ok(productService.searchByKeyword(storeId,keyword));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> productById(@PathVariable("id") Long productId){
        return ResponseEntity.ok(productService.getProductById(productId,null));
    }

    @GetMapping("/productsbyadminid/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByAdminId(@PathVariable("id") Long adminId){
        return ResponseEntity.ok(productService.getProductsByAdminId(adminId));

    }

}
