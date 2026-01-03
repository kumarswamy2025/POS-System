package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.BranchService;
import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.exceptions.BranchException.BranchException;
import com.possystem.mainapplication.mapper.BranchMapper;
import com.possystem.mainapplication.modal.BranchModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.BranchDTO;
import com.possystem.mainapplication.repository.BranchRepo;
import com.possystem.mainapplication.repository.StoreRepo;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImplementation implements BranchService {

    //    repos dependencies
    private final BranchRepo branchRepo;
    private final StoreRepo storeRepo;

    //    service dependencies
    private final UserService userService;
    private final StoreService storeService;
    private final UserRepo userRepo;


    @Override
    public BranchDTO createBranch(BranchDTO branchDTO, UserModal userModal) {

//        we get current user and stores it have
        UserModal currentUser = userService.getCurrentUser();
        StoreModal storeModal = storeRepo.findByStoreAdminId(currentUser.getId());

//        checking if store is exists or not
        if (storeModal == null) {
            throw new BranchException("store is not found....", HttpStatus.NOT_FOUND);
        }
//        checking if branchDTO.getStoreId()  is mathed with storeModal.getId()
        if ((branchDTO.getId() != null) && (storeModal.getId() != branchDTO.getStoreId())) {
            throw new BranchException("store id is not matched to user mapped store please check once ", HttpStatus.BAD_REQUEST);
        }


//       create branch entity
        BranchModal branchModal = BranchMapper.toEntity(branchDTO, storeModal, userModal);
//        save entity
        BranchModal savedData = branchRepo.save(branchModal);

//        converting entity to dto
        BranchDTO dto = BranchMapper.toDTO(savedData, storeModal, userModal);


        return dto;
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO, UserModal userModal) {

//        check branch is exits or not
        BranchModal branchModal = branchRepo.findById(id).orElseThrow(
                () -> new BranchException("branch is not found...", HttpStatus.NOT_FOUND)
        );

        StoreModal storeModal=storeRepo.findById(branchModal.getStore().getId()).orElseThrow(
                ()-> new BranchException("store is not found...",HttpStatus.NOT_FOUND)
        );
//        checking if name is not null then update branch  name
        if (branchDTO.getName() != null) {
            branchModal.setName(branchDTO.getName());
        }
        //        checking if Address is not null then update branch  Address

        if (branchDTO.getAddress() != null) {
            branchModal.setAddress(branchDTO.getAddress());
        }
        //        checking if Phone is not null then update branch  Phone
        if (branchDTO.getPhone() != null) {
            branchModal.setPhone(branchDTO.getPhone());
        }
        //        checking if email is not null then update branch  email
        if (branchDTO.getEmail() != null) {
            branchModal.setEmail(branchDTO.getEmail());
        }
        //        checking if WorkingDays is not null then update branch  WorkingDays
        if (branchDTO.getWorkingDays() != null) {
            branchModal.setWorkingDays(branchDTO.getWorkingDays());
        }
        //        checking if OpenTime is not null then update branch  OpenTime
        if (branchDTO.getOpenTime() != null) {
            branchModal.setOpenTime(branchDTO.getOpenTime());
        }
        //        checking if CloseTime is not null then update branch  CloseTime
        if (branchDTO.getCloseTime() != null) {
            branchModal.setCloseTime(branchDTO.getCloseTime());
        }
//        checking if store id is not null then update branch store id
        if(branchDTO.getStoreId()!=null){
//            checking if store is exits or not
            StoreModal storeModal1=storeRepo.findById(branchDTO.getStoreId()).orElseThrow(
                    ()-> new BranchException("store is not found...",HttpStatus.NOT_FOUND)
            );
//            here we update store id
            branchModal.setStore(storeModal1);
        }

//        save data
        BranchModal savedData=branchRepo.save(branchModal);

//        convert modal to dto

        return BranchMapper.toDTO(savedData,storeModal,userModal);
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        BranchModal branchModal=branchRepo.findById(id).orElseThrow(
                ()-> new BranchException("Branch not found..",HttpStatus.NOT_FOUND)
        );

        StoreModal storeModal=storeRepo.findById(branchModal.getStore().getId()).orElseThrow(
                ()-> new BranchException("store not found...",HttpStatus.NOT_FOUND)
        );
        UserModal userModal=userRepo.findById(branchModal.getManager().getId()).orElseThrow(
                ()-> new BranchException("user not found...",HttpStatus.NOT_FOUND)
        );
        BranchDTO dto=BranchMapper.toDTO(branchModal,storeModal,userModal);


        return dto;
    }

    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId) {
        List<BranchModal> branchModals=branchRepo.findByStoreId(storeId);
//        checking id branch modal is null
        if(branchModals==null){
            throw new BranchException("no branches are found...",HttpStatus.BAD_REQUEST);
        }
       return branchModals.stream().map(branch->BranchMapper.toDTO(
               branch,branch.getStore(),branch.getManager()
       )).collect(Collectors.toList());

    }

    @Override
    public void deleteBranch(Long id) {
//        checking if branch id is exits or not
        BranchModal branchModal=branchRepo.findById(id).orElseThrow(
                ()-> new BranchException("branch id not found...",HttpStatus.NOT_FOUND)
        );

        branchRepo.delete(branchModal);

    }
}
