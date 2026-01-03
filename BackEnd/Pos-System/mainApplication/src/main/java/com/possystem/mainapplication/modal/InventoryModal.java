package com.possystem.mainapplication.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class InventoryModal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
