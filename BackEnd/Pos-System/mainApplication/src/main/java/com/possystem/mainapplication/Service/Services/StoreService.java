package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.StoreDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO, UserModal userModal);
    StoreDTO getStoreById(Long id);
    List<StoreDTO> getAllStores();
    StoreModal getStoreByAdmin();
    StoreDTO updateStore(Long id,StoreDTO storeDTO);
    StoreDTO deleteStore(Long id);
    StoreDTO getStoreByEmployee();



}
