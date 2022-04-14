package com.digiboy.platform.user.mapper;

import com.digiboy.platform.user.dto.UserDTO;
import com.digiboy.platform.user.to.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends EntityMapper<User, UserDTO> {
}