package com.possystem.mainapplication.Service.Implementation;

import com.possystem.mainapplication.Service.Services.ProductService;
import com.possystem.mainapplication.Service.Services.StoreService;
import com.possystem.mainapplication.Service.Services.UserService;
import com.possystem.mainapplication.exceptions.CategoryException.CategoryException;
import com.possystem.mainapplication.exceptions.ProductException.ProductException;
import com.possystem.mainapplication.mapper.ProductMapper;
import com.possystem.mainapplication.modal.CategoryModal;
import com.possystem.mainapplication.modal.ProductModal;
import com.possystem.mainapplication.modal.StoreModal;
import com.possystem.mainapplication.modal.UserModal;
import com.possystem.mainapplication.payload.DTO.ProductDTO;
import com.possystem.mainapplication.repository.CategoryRepo;
import com.possystem.mainapplication.repository.ProductRepo;
import com.possystem.mainapplication.repository.StoreRepo;
import com.possystem.mainapplication.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    //    here we inject repos
    private final ProductRepo productRepo;
    private final StoreRepo storeRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    //    here we inject services
    private final UserService userService;
    private final StoreService storeService;


    @Override
    public ProductDTO createProduct(ProductDTO productDTO, UserModal userModal) {
//        here we get store id from client
        //        first check if store is present or not
        StoreModal storeModal = storeRepo.findById(productDTO.getStoreId()).orElseThrow(() -> new ProductException("Store Not Found", HttpStatus.NOT_FOUND));

//        checking if store is exits or not
        CategoryModal categoryModal=categoryRepo.findById(productDTO.getCategoryId()).orElseThrow(
                ()-> new ProductException("category is not found in product creation..",HttpStatus.NOT_FOUND)
        );

//        converting dto to entity
        ProductModal productModal = ProductMapper.toEntity(productDTO, storeModal,categoryModal);
//        saveing data to DB
        ProductModal savedData = productRepo.save(productModal);
//        converting modal to dto
        ProductDTO productDTO1 = ProductMapper.toDTO(savedData);
        return productDTO1;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, UserModal userModal) {
//        here we get store id from client
//        first check if product is present or not
        ProductModal productModal = productRepo.findById(id).orElseThrow(() -> new ProductException("Product Not Found", HttpStatus.NOT_FOUND));

//        check if storeid is not null
        if (productDTO.getStoreId() != null) {
            //        here we get store id from client
            //        first check if store is present or not
            StoreModal storeModal = storeRepo.findById(productDTO.getStoreId()).orElseThrow(() -> new ProductException("Store Not Found", HttpStatus.NOT_FOUND));

//            here we update store id
            productModal.setStore(storeModal);

        }


//        checking if category id is valid to update
        if(productDTO.getCategoryId()!=null){
//            checking catory id is valid
            CategoryModal modal=categoryRepo.findById(productDTO.getCategoryId()).orElseThrow(
                    ()-> new CategoryException("Category id is not valid to update category id ",HttpStatus.BAD_REQUEST)
            );
//            IF valid then update the categpry id
            productModal.setCategory(modal);


        }



//      checking if name is not null then update it
        if (productDTO.getName() != null) {
            productModal.setName(productDTO.getName());
        }

        //      checking if sku is not null then update it
        if (productDTO.getSku() != null) {
            productModal.setSku(productDTO.getSku());
        }
        //      checking if description is not null then update it
        if (productDTO.getDescription() != null) {
            productModal.setDescription(productDTO.getDescription());
        }
        //      checking if mrp is not null then update it
        if (productDTO.getMrp() !=null) {
            productModal.setMrp(productDTO.getMrp());
        }

        //      checking if sellingPrice is not null then update it
        if (productDTO.getSellingPrice() != null) {
            productModal.setSellingPrice(productDTO.getSellingPrice());
        }
        //      checking if brand is not null then update it
        if (productDTO.getBrand() != null) {
            productModal.setBrand(productDTO.getBrand());
        }
        //      checking if image is not null then update it
        if (productDTO.getImage() != null) {
            productModal.setImage(productDTO.getImage());
        }

//        save updated product
        ProductModal savedData = productRepo.save(productModal);

//        converte modal to dto
        ProductDTO DTO = ProductMapper.toDTO(savedData);
        return DTO;
    }

    @Override
    public void deleteProduct(Long id, UserModal userModal) {
//        logic is simple we have to delete products belongs to one store and deleted by store admin only


//        current user
        UserModal userModal1 = userService.getCurrentUser();
//        here we get user mapped to store
        Long StoreId = userModal1.getStore().getId();
//        here we get

        if(StoreId==null){
            throw new ProductException("store id is not found in user modal",HttpStatus.NOT_FOUND);
        }
        //        here we get store id from client
//        first check if product is present or not
        ProductModal productModal = productRepo.findByIdAndByStoreId(id, StoreId);

        if (productModal == null) {
            throw new ProductException("product is not found to attached store", HttpStatus.NOT_FOUND);
        }

//        delete product
        productRepo.delete(productModal);
    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
        //        this method is used to all  get products in store
//        here we receive list of products data modals
        List<ProductModal> productModal = productRepo.findByStoreId(storeId);
//        here we convert products modals into DTOs
//        here we use streams
        return productModal.stream().map(ProductMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {

//        here we receive list of products data modals
        List<ProductModal> productModal = productRepo.searchByKeyword(storeId, keyword);
//        here we convert products modals into DTOs
//        here we use streams
        return productModal.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id, UserModal userModal) {
        ProductModal productModal = productRepo.findById(id).orElseThrow(() -> new ProductException("product is not found..", HttpStatus.NOT_FOUND));

        ProductDTO productDTO = ProductMapper.toDTO(productModal);

        return productDTO;
    }

    @Override
    public List<ProductDTO> getProductsByAdminId(Long adminId) {

//        checking if admin id null then use current user id as admin id
        if (adminId == null) {
            UserModal userModal = userService.getCurrentUser();
            adminId = userModal.getId();
        }

//        this method is used to all  get products with admin id
        List<ProductModal> productModal = productRepo.findProductsByAdminId(adminId);
        if(productModal.isEmpty()){
            throw new ProductException("not products mapped to the user",HttpStatus.BAD_REQUEST);
        }
        return productModal.stream().map(ProductMapper::toDTO).collect(Collectors.toList());


    }

}
