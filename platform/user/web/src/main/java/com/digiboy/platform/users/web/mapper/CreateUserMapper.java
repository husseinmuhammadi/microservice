package com.digiboy.platform.users.web.mapper;

import com.digiboy.platform.users.dto.UserDTO;
import com.digiboy.platform.users.web.model.CreateUserRequest;
import com.digiboy.platform.users.web.model.CreateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper()
public interface CreateUserMapper {

    @Mapping(target = "password", ignore = true)
    UserDTO map(CreateUserRequest request);

    CreateUserResponse map(UserDTO user);

}
