package com.possystem.mainapplication.Service.Services;

import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.StoreDTO;
import org.springframework.stereotype.Service;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO, UserModal userModal);


}
