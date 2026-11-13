package uz.pdp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.config.JwtTokenUtil;
import uz.pdp.dtos.LoginDto;
import uz.pdp.dtos.RegisterDto;
import uz.pdp.entity.User;
import uz.pdp.enums.Role;
import uz.pdp.repository.User_Repository;

@RestController
public class AuthController {

    private final User_Repository user_Repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(User_Repository user_Repository, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.user_Repository = user_Repository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(" /api/auth/register")
    public ResponseEntity<Void> register(@Valid  @RequestBody RegisterDto dto){
       User user= User.builder().username(dto.getUsername()).email(dto.getEmail()).password(dto.getPassword()).role(Role.USER).build();
       user_Repository.save(user);
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }


    @PostMapping(" /api/auth/login")
    public String login(@RequestBody LoginDto dto) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

       authenticationManager.authenticate(authentication);

        return jwtTokenUtil.generateToken(dto.getUsername() );
    }

}
