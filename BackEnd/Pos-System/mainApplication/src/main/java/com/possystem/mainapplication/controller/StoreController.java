package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.StoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO, @RequestHeader("Authorization") String jwt) {
//         here we getting  user modal  with jwt
        UserModal userData = userService.getUserFromJWTToken(jwt);
//         calling create store service
        StoreDTO res = storeService.createStore(storeDTO, userData);
        return ResponseEntity.ok(res);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable("id") Long id){

       StoreDTO dto= storeService.getStoreById(id);

       return ResponseEntity.ok(dto);
    }

//     getting all stores
    @GetMapping("/allstores")
    public ResponseEntity<List<StoreDTO>> getAllStore(){
        List<StoreDTO> dto=storeService.getAllStores();

       return ResponseEntity.ok(dto);

    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin(){
       return ResponseEntity.ok(storeService.getStoreByAdmin());
    }

     @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(){
        return ResponseEntity.ok(storeService.getStoreByEmployee());
     }


     @PutMapping("/updatestore/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable("id") Long id,@RequestBody StoreDTO storeDTO){
        StoreDTO dto=storeService.updateStore(id,storeDTO);

        return ResponseEntity.ok(dto);
     }


     @DeleteMapping("/storeDelete/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable("id") Long id){

         HashMap<String,String> map=new HashMap<>();
         map.put("message:","Deleted Successfully");

         return ResponseEntity.ok(map);


     }




}
