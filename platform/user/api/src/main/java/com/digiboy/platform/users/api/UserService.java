package com.digiboy.platform.users.api;

import com.digiboy.platform.users.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> findAll(Pageable pageable);

    UserDTO save(UserDTO userDTO);
}
