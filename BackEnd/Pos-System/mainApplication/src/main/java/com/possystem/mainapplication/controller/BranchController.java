package com.possystem.mainapplication.controller;

import com.possystem.mainapplication.Service.Services.BranchService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.BranchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branch")
public class BranchController {
    private final BranchService branchService;
    private final UserService userService;

//    tested successfully
//    create branch
    @PostMapping("/create")
    public ResponseEntity<BranchDTO> create(@RequestBody  BranchDTO branchDTO, @RequestHeader("Authorization") String jwt){
    UserModal currentUser=    userService.getUserFromJWTToken(jwt);

        return ResponseEntity.ok(branchService.createBranch(branchDTO,currentUser));
    }
    //    tested successfully
//    get branch  by id
    @GetMapping("/branch/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable("id") Long id){
        return ResponseEntity.ok(branchService.getBranchById(id));
    }
    //    tested successfully"
//    update branch
    @PutMapping("/update/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable("id") Long id,@RequestBody BranchDTO  branchDTO,@RequestHeader("Authorization") String jwt){
        UserModal currentUser=    userService.getUserFromJWTToken(jwt);

        return ResponseEntity.ok(branchService.updateBranch(id,branchDTO,currentUser));
    }

    //    tested successfully
    //    get all branches by store id
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchesByStoreId(@PathVariable("storeId") Long storeId){
        return ResponseEntity.ok(branchService.getAllBranchesByStoreId(storeId));
    }
    //    tested successfully
//    delete branch
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable("id") Long id){

        branchService.deleteBranch(id);
        HashMap<String, String> res=new HashMap<>();
        res.put("branch id :",""+id);
        res.put("message : ","branch is deleted successfully..");
        return ResponseEntity.ok(res);

    }



}
