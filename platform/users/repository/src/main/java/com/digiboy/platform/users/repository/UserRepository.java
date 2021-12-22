package com.digiboy.platform.users.repository;

import com.digiboy.platform.users.to.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
