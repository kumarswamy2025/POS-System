package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.InventoryService;
import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.exceptions.InventoryException.InventoryException;
import com.possystem.mainapplication.mapper.BranchMapper;
import com.possystem.mainapplication.mapper.InventoryMapper;
import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.InventoryModal;
import com.possystem.mainapplication.modal.ProductModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.InventoryDTO;
import com.possystem.mainapplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImplementation implements InventoryService {

    //    here we inject repos
    private final ProductRepo productRepo;
    private final BranchRepo branchRepo;
    private final InventoryRepo inventoryRepo;

    //    here we inject services
    private final UserService userService;


    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
//        checking branch is exixts or not
        BranchModal branchModal = branchRepo.findById(inventoryDTO.getBranchId()).orElseThrow(() -> new InventoryException("branch modal is not found...", HttpStatus.NOT_FOUND));
//        checking product is exists or not
        ProductModal productModal = productRepo.findById(inventoryDTO.getProductId()).orElseThrow(() -> new InventoryException("Product modal is not found...", HttpStatus.NOT_FOUND));

        UserModal userModal = userService.getCurrentUser();

//        converting dto to entity
        InventoryModal modal = InventoryMapper.toEntity(inventoryDTO, branchModal, productModal);

//        save data to DB
        InventoryModal savedData = inventoryRepo.save(modal);

//        convert entoty to DTO
        InventoryDTO dto = InventoryMapper.toDTO(savedData, null);


        return dto;
    }

    // here we update only quantity
    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
//        checking if inventory is exits or not
        InventoryModal modal = inventoryRepo.findById(id).orElseThrow(() -> new InventoryException("Inventory not found...", HttpStatus.NOT_FOUND));
// checking if quatity is not null
        if (inventoryDTO.getQuantity() != null) {
//            update quatity
            modal.setQuantity(inventoryDTO.getQuantity());
        }

//        save data toDB
        InventoryModal savedData = inventoryRepo.save(modal);
//        entity to DTO
        InventoryDTO dto = InventoryMapper.toDTO(savedData, null);
        return dto;
    }

    @Override
    public void deleteInventory(Long id) {


//        checking if inventory is exits or not
        InventoryModal modal = inventoryRepo.findById(id).orElseThrow(() -> new InventoryException("Inventory not found...", HttpStatus.NOT_FOUND));

//        delete inventory
        inventoryRepo.delete(modal);

    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        //        checking if inventory is exits or not
        InventoryModal modal = inventoryRepo.findById(id).orElseThrow(() -> new InventoryException("Inventory not found...", HttpStatus.NOT_FOUND));

//        modal to dto
        InventoryDTO dto = InventoryMapper.toDTO(modal, null);

        return dto;
    }

    @Override
    public List<InventoryDTO> getInventoryByProductId(Long productId) {
        List<InventoryModal> inventoryModals = inventoryRepo.findByProductId(productId);

//        convert modals to dto
      return   inventoryModals.stream().map(m -> InventoryMapper.toDTO(
                m,null
        )).collect(Collectors.toList());


    }

    @Override
    public List<InventoryDTO> getInventoryByBranchId(Long branchId) {
        List<InventoryModal> inventoryModals = inventoryRepo.findByBranchId(branchId);

//        convert modals to dto
        return   inventoryModals.stream().map(m -> InventoryMapper.toDTO(
                m,null
        )).collect(Collectors.toList());
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        InventoryModal modal=inventoryRepo.findByProductIdAndBranchId(productId,branchId);
        if(modal==null){
            throw new InventoryException("no modals are found..",HttpStatus.NOT_FOUND);
        }
//        modal to dto
        InventoryDTO dto=InventoryMapper.toDTO(modal,null);
        return dto;
    }

    @Override
    public List<InventoryDTO> getAllInventories() {
        List<InventoryModal> inventoryModals=inventoryRepo.findAll();

//        modals to dto
     return    inventoryModals.stream().map(
                b->InventoryMapper.toDTO(
                        b,null
                )
        ).collect(Collectors.toList());


    }
}
