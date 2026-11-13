package uz.pdp.config.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.entity.User;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private User User;

    public CustomUserDetails( User user) {
        this.User = user;
    }



    @Override
    public String getUsername() {
        return User.getUsername();
    }

    @Override
    public @Nullable String getPassword() {
        return User.getPassword();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority( User.getRole().name() ) );
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

