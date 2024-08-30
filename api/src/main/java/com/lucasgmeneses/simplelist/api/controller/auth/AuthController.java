package com.lucasgmeneses.simplelist.api.controller.auth;

import com.lucasgmeneses.simplelist.api.dto.auth.LoginRequestDTO;
import com.lucasgmeneses.simplelist.api.dto.auth.LoginResponseDTO;
import com.lucasgmeneses.simplelist.api.dto.auth.RegisterRequestDTO;
import com.lucasgmeneses.simplelist.api.dto.auth.RegisterResponseDTO;
import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import com.lucasgmeneses.simplelist.api.repository.auth.UserRepository;
import com.lucasgmeneses.simplelist.api.service.auth.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        UserModel user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getNickname(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<UserModel> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            UserModel newUser = new UserModel();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setNickname(body.nickname());
            newUser.setDateCreated(new Date());
            newUser.setDateUpdated(new Date());
            this.repository.save(newUser);

            return ResponseEntity.ok(new RegisterResponseDTO(newUser.getNickname(), newUser.getDateCreated()));
        }
        return ResponseEntity.badRequest().build();
    }
}
