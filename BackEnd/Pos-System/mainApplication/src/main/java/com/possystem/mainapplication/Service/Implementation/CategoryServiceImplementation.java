package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.CategoryService;
import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.exceptions.CategoryException.CategoryException;
import com.possystem.mainapplication.exceptions.StoreException.StoreException;
import com.possystem.mainapplication.mapper.CategoryMapper;
import com.possystem.mainapplication.modal.CategoryModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.CategoryDTO;
import com.possystem.mainapplication.payload.DTO.StoreDTO;
import com.possystem.mainapplication.repository.CategoryRepo;
import com.possystem.mainapplication.repository.StoreRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {
    //    here we inject repos
      private final StoreRepo storeRepo;
      private final CategoryRepo categoryRepo;


    //    here we inject services
      private final StoreService storeService;
      private final UserService userService;

//      here we inject modal mapper
    private final ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        checking if user is valid or not
        UserModal userModal = userService.getCurrentUser();

//        checking if store is exits or not
        StoreModal store=storeRepo.findById(categoryDTO.getStoreId()).orElseThrow(
                ()-> new CategoryException("Store not found..", HttpStatus.NOT_FOUND)
        );
//        convert dto to entity
        CategoryModal modal= CategoryMapper.toEntity(categoryDTO,store);

        //       Check authority before delete it
        checkAuthority(userModal,modal.getStore());
//        save data to DB
       CategoryModal savedData= categoryRepo.save(modal);

//       converting entity to DTO
        CategoryDTO dto=modelMapper.map(savedData,CategoryDTO.class);

        return dto;
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {

//        check store is exists or not
        StoreDTO storeDTO = storeService.getStoreById(storeId);

//        get all categories in  a store
        List<CategoryModal> categoryModals=categoryRepo.findByStoreId(storeId);

//        now converting list of  all modals to dto using streams
        return categoryModals.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
//        this method is used to update category like name, store
//        check id category exits or not
        CategoryModal modal=categoryRepo.findById(id).orElseThrow(
                ()-> new CategoryException("category is not found..",HttpStatus.NOT_FOUND)
        );

//        check if user is valid or not
        UserModal user=userService.getCurrentUser();

//        now upadates things
//   checking if name is not null then update category name
        if(categoryDTO.getName()!=null){
            modal.setName(categoryDTO.getName());
        }
//        checking if store id is not null then update store data
        if(categoryDTO.getStoreId()!=0.0){
//            first we have to find store is exits or not
            StoreModal storeModal = storeRepo.findById(id).orElseThrow(

                    () -> new StoreException("Store is not found....", HttpStatus.NOT_FOUND));
//            now update category modal
            modal.setStore(storeModal);
        }
//       Check authority before delete it
        checkAuthority(user,modal.getStore());

//        now save data to DB
     CategoryModal savedData=   categoryRepo.save(modal);

        return modelMapper.map(savedData,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {
        //        check if user is valid or not
        UserModal user=userService.getCurrentUser();

//        checking if category is exits or not
       CategoryModal modal= categoryRepo.findById(id).orElseThrow(
               ()-> new CategoryException("Category is not found...",HttpStatus.NOT_FOUND)
       );

//       Check authority before delete it
        checkAuthority(user,modal.getStore());

//        delete it

              categoryRepo.delete(modal);

    }
    @Override
    public void deleteCategoryByStoreId(Long id, Long storeId){

        //        checking if user is valid or not
        UserModal userModal = userService.getCurrentUser();

        //        checking if category is exits or not
        CategoryModal modal= categoryRepo.findById(id).orElseThrow(
                ()-> new CategoryException("Category is not found...",HttpStatus.NOT_FOUND)
        );

        StoreModal storeModal=storeRepo.findById(storeId).orElseThrow(
                ()-> new CategoryException("store is not found...",HttpStatus.NOT_FOUND)
        );

//        NOW delete category belongs to that store
        CategoryModal modal1=categoryRepo.findCategoryByIdAndStoreId(id,storeId);
//       Check authority before delete it
        checkAuthority(userModal,modal.getStore());

        categoryRepo.delete(modal1);




    }

    private void checkAuthority(UserModal userModal,StoreModal storeModal){
        boolean isAdmin=userModal.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager=userModal.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore=userModal.equals(storeModal.getStoreAdmin());
        if(!(isAdmin && isSameStore) && !isManager){
            throw new CategoryException("you don't have a permission to manage this category",HttpStatus.UNAUTHORIZED);
        }

    }

}
