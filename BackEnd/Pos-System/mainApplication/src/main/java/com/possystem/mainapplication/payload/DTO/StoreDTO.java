package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.domain.StoreStatus;
import com.possystem.mainapplication.modal.StoreAddress;


import java.time.LocalDateTime;

public class StoreDTO {

    private Long Id;

    private String brand;

    private UserDTO storeAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String storeType;
    private StoreStatus status;

    private StoreAddress contact;
}
