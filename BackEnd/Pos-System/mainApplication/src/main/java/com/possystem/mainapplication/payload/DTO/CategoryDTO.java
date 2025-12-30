package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.modal.StoreModal;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;

//    private StoreModal store;
    private Long storeId;



}
