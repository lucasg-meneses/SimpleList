package com.lucasgmeneses.simplelist.api.repository.auth;

import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);
}
