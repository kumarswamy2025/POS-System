package com.possystem.mainapplication.payload.DTO;

import com.possystem.mainapplication.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor   // ✅ REQUIRED
@AllArgsConstructor
public class UserProfileDTO {

    private Long ID;
    private String fullName;
    private String email;
    private String phone;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

}
