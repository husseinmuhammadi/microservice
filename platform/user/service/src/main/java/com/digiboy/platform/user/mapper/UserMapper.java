package com.digiboy.platform.user.mapper;

import com.digiboy.platform.user.dto.UserDTO;
import com.digiboy.platform.user.to.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper extends EntityMapper<User, UserDTO> {

    @Override
    @Mapping(target = "password", source = "encryptedPassword")
    User map(UserDTO dto);

    List<UserDTO> map(List<User> entities);
}