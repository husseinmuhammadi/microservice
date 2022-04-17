package com.digiboy.platform.user.repository;

import com.digiboy.platform.user.to.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    void userIdShouldGenerated() {
        User user = new User();
        UUID userId = UUID.randomUUID();
        user.setUserId(userId);
        User savedUser = repository.save(user);
        assertThat(savedUser.getUserId()).isNotNull();
        assertThat(savedUser.getUserId()).isNotEqualTo(userId);
    }

    @Test
    void usernameIsUnique() {
        User user1 = new User();
        user1.setUsername("username");
        User user2 = new User();
        user2.setUsername("username");
        repository.save(user1);
        repository.save(user2);

        assertThrows(DataIntegrityViolationException.class, () -> repository.flush());
    }

    @Test
    void itIsPossibleToSaveToUserWithEmptyUsername() {
        User user1 = new User();
        User user2 = new User();
        repository.save(user1);
        repository.save(user2);
        assertDoesNotThrow(() -> repository.flush());
    }

}