package com.digiboy.platform.users.mapper;

import com.digiboy.platform.users.dto.UserDTO;
import com.digiboy.platform.users.to.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends EntityMapper<User, UserDTO> {
}