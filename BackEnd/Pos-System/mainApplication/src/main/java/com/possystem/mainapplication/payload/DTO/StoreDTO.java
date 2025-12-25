package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.domain.StoreStatus;
import com.possystem.mainapplication.modal.StoreAddress;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
@Data
@Getter
@Setter
@Builder
public class StoreDTO {

    private Long id;

    private String brand;

    private UserDTO storeAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String storeType;
    private StoreStatus status;

    private StoreAddress contact;
}
