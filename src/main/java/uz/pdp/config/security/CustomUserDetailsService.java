package uz.pdp.config.security;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.entity.User;
import uz.pdp.repository.User_Repository;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final User_Repository UserRepository;

    public CustomUserDetailsService(User_Repository UserRepository) {
        this.UserRepository =UserRepository;
    }

    @Override
    public UserDetails loadUserByUsername( @NonNull String username) throws UsernameNotFoundException {

        Optional<User> optional = UserRepository.findUserByUsername(username);
        User user = optional.orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));

        return new CustomUserDetails(user);
    }



}

