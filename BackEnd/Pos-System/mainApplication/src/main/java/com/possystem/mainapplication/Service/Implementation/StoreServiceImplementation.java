package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.mapper.StoreMapper;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.StoreDTO;
import com.possystem.mainapplication.repository.StoreRepo;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImplementation implements StoreService {

    private final UserRepo userRepo;
    private final StoreRepo storeRepo;
    private final ModelMapper modelMapper;


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

        return null;
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return List.of();
    }

    @Override
    public StoreModal getStoreByAdmin() {
        return null;
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) {
        return null;
    }

    @Override
    public StoreDTO deleteStore(Long id) {
        return null;
    }

    @Override
    public StoreDTO getStoreByEmployee() {
        return null;
    }
}
