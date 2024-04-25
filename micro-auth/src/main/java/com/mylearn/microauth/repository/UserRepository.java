package com.mylearn.microauth.repository;

import com.mylearn.microauth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsernameAndIsEnabled(String username,Boolean isEnabled);
}
