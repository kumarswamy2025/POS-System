package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.domain.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private UserRole role;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

//    this is new fields for employee service implementation
    private Long branchId;
    private Long storeId;
    private UserStatus userStatus;

}
