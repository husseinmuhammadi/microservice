package com.digiboy.platform.user.api;

import com.digiboy.platform.user.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> findAll(Pageable pageable);

    UserDTO save(UserDTO userDTO);

    boolean exists(String username);
}
