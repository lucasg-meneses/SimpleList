package com.lucasgmeneses.simplelist.api.controller.user;

import com.lucasgmeneses.simplelist.api.dto.user.UserRequestDTO;
import com.lucasgmeneses.simplelist.api.dto.user.UserResponseDTO;
import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import com.lucasgmeneses.simplelist.api.repository.auth.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping
    public ResponseEntity getUser(@AuthenticationPrincipal UserModel user){
            return ResponseEntity.ok(new UserResponseDTO(user.getNickname(), user.getEmail(), user.getDateUpdated()));
    }

    @PutMapping
    public ResponseEntity updateUser(@AuthenticationPrincipal UserModel user, @RequestBody UserRequestDTO request){
        user.setNickname(request.nickname());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setDateUpdated(new Date());
        repository.save(user);
        return ResponseEntity.ok(new UserResponseDTO(user.getNickname(), user.getEmail(), user.getDateUpdated()));
    }
}
