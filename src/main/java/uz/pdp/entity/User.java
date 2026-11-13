package uz.pdp.entity;

import lombok.*;
import jakarta.persistence.*;
import uz.pdp.enums.Role;
import uz.pdp.enums.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "user_name", nullable = false)
        private String username;
        @Column(name = "email",nullable = false)
        private String email;
        @Column(name = "password")
        private String password;
        @Enumerated(EnumType.STRING)
        private Role role;
        private Status status;

}
