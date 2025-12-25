package com.possystem.mainapplication.modal;

import com.possystem.mainapplication.domain.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserModal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid...")
    private String email;
    private String phone;
    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false)
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    @ManyToOne
    private StoreModal store;


}
