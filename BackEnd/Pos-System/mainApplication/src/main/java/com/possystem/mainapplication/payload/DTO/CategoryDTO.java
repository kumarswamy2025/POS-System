package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.modal.StoreModal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
