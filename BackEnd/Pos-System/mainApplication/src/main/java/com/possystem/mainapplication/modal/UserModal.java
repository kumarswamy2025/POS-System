package com.possystem.mainapplication.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserModal {

    @Id
    private  Long ID;

    @Column(nullable = false)
    private  String fullName;

    @Column(nullable = false,unique = true)
    @Email(message = "Email should be valid...")
    private String email;




}
