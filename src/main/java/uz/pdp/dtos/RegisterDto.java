package uz.pdp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private Role role;
}
