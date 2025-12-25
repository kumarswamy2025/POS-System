package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.exceptions.StoreException.StoreException;
import com.possystem.mainapplication.exceptions.UserException.UserExceptions;
import com.possystem.mainapplication.mapper.StoreMapper;
import com.possystem.mainapplication.modal.StoreAddress;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.StoreDTO;
import com.possystem.mainapplication.repository.StoreRepo;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImplementation implements StoreService {

    private final UserRepo userRepo;
    private final StoreRepo storeRepo;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @Override
    public StoreDTO createStore(StoreDTO storeDTO, UserModal userModal) {

//        here iam using model mapper
//        we converting dto to entity
        StoreModal SM = modelMapper.map(storeDTO, StoreModal.class);

        System.out.println("this model mapper  Store modal:" + SM);
//        we converting entity to dto
        StoreDTO SD = modelMapper.map(SM, StoreDTO.class);
        System.out.println("this model mapper  Store DTO:" + SD);


//        DTO to entity
        StoreModal storeModal = StoreMapper.toEntity(storeDTO, userModal);
        StoreModal savedData = storeRepo.save(storeModal);

//        entity to DTO
        StoreDTO dto = StoreMapper.toDTO(savedData);


        return dto;
    }

    @Override
    public StoreDTO getStoreById(Long id) {
        StoreModal store = storeRepo.findById(id).orElseThrow(

                () -> new StoreException("Store is not found....", HttpStatus.NOT_FOUND));


//        conerting entity  to DTO and return it
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        List<StoreModal> storeModals = storeRepo.findAll();
        return storeModals.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public StoreModal getStoreByAdmin() {
//        note : one admin for one store
        UserModal admin = userService.getCurrentUser();
        return storeRepo.findByStoreAdminId(admin.getID());
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) {
//        here we get current logedIn user data and stores are mapped by him we can edit it
        UserModal currentUser = userService.getCurrentUser();
//        here we get stores mapped by users
        StoreModal existingStore = storeRepo.findByStoreAdminId(currentUser.getID());

        if (existingStore == null) {
            throw new StoreException("Store is not found..", HttpStatus.NOT_FOUND);
        }

//        here we update store details
        existingStore.setBrand(storeDTO.getBrand());

        existingStore.setDescription(storeDTO.getDescription());

//        checking if store type is not null and checking  it is provided by store dto
        if (storeDTO.getStoreType() != null) {
            existingStore.setStoreType(storeDTO.getStoreType());
        }
//        checking if contact  is not null and checking  it is provided by store dto

        if (storeDTO.getContact() != null) {
            StoreAddress address = StoreAddress.builder().address(storeDTO.getContact().getAddress()).phone(storeDTO.getContact().getPhone()).email(storeDTO.getContact().getEmail()).build();

//            insert into existing store
            existingStore.setContact(address);
        }
//        save updates
        StoreModal savedStore = storeRepo.save(existingStore);

//        here we convert modal to dto

        StoreDTO dto = StoreMapper.toDTO(savedStore);
        return dto;
    }

    @Override
    public void deleteStore(Long id) {
//        here we get store data
        //        note : one admin for one store
        StoreModal store = getStoreByAdmin();

//        delete store
        storeRepo.delete(store);
    }

    @Override
    public StoreDTO getStoreByEmployee() {
        UserModal currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new UserExceptions("you don't have permissions to access this store", HttpStatus.FORBIDDEN);
        }
        return StoreMapper.toDTO(currentUser.getStore());
    }
}
