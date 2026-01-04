package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.InventoryService;
import com.possystem.mainapplication.payload.DTO.InventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

//    tested successfully
    @PostMapping("/create")
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO inventoryDTO){

        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));
    }
    //    tested successfully
    @PutMapping("/update/{id}")
    public ResponseEntity<InventoryDTO> update(@RequestBody InventoryDTO inventoryDTO,@PathVariable("id") Long id){
        return ResponseEntity.ok(inventoryService.updateInventory(id,inventoryDTO));
    }

    //    tested successfully
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        inventoryService.deleteInventory(id);
        HashMap<String, String> res=new HashMap<>();
        res.put("Inventory id :",""+id);

        res.put("message : ","Inventory  is deleted successfully..");
        return ResponseEntity.ok(res);
    }

    //    tested successfully
    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }
    //    tested successfully
    @GetMapping("/inventorybyproduct/{productid}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByProductId(@PathVariable("productid") Long productId){
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
    }
    //    tested successfully
    @GetMapping("/inventorybybranch/{branchid}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByBranchId(@PathVariable("branchid") Long branchId){
        return ResponseEntity.ok(inventoryService.getInventoryByBranchId(branchId));
    }
    //    tested successfully
    @GetMapping("/inventorybyproductbybranch/{productid}/{branchid}")
    public ResponseEntity<InventoryDTO> getInventoryByProductIdAndBranchId(@PathVariable("productid") Long productId,@PathVariable("branchid") Long branchId){
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));
    }
    //    tested successfully
    @GetMapping("/allinventories")
    public ResponseEntity<List<InventoryDTO>> getAllInventories(){
        return ResponseEntity.ok(inventoryService.getAllInventories());

    }


}
