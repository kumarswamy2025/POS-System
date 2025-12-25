package com.possystem.mainapplication.modal;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreAddress {
    private String address;
    private  String phone;
    @Email(message = "invalid email format")
    private String email;



}
