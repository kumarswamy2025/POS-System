package com.possystem.mainapplication.modal;

import com.possystem.mainapplication.domain.UserRole;
import com.possystem.mainapplication.domain.UserStatus;
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
//    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false)
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id") // FK lives here
    private StoreModal store;
    private UserStatus userStatus;


    // Runs ONLY when new user is inserted
    @PrePersist
    protected void onCreate() {

        this.createdAt = LocalDateTime.now();

//        when new user creates then user status is pending
        this.userStatus=UserStatus.PENDING;

        // set default role only if not provided
        if (this.role == null ) {
            this.role = UserRole.NO_ROLE;   // default role
        }
    }

    // Runs EVERY TIME when user is updated
    @PreUpdate
    protected void onUpdate() {

        this.updatedAt = LocalDateTime.now();

        // Example: If role is null accidentally during update
//        if (this.role == null) {
//            this.role = UserRole.NO_ROLE;
//        }
    }


}
