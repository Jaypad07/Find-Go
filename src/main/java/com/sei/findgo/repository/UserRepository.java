package com.sei.findgo.repository;

import com.sei.findgo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findUserByEmail(String email);
}
