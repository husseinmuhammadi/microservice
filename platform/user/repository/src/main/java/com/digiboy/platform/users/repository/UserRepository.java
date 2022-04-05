package com.digiboy.platform.users.repository;

import com.digiboy.platform.users.to.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);
}
