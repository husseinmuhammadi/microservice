package com.digiboy.platform.user.web.mapper;

import com.digiboy.platform.user.dto.UserDTO;
import com.digiboy.platform.user.generated.v1.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserDetailsMapper {
    @Mapping(target = "password", source = "encryptedPassword")
    UserDetails map(UserDTO dto);
}
