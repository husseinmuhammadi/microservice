package com.digiboy.platform.user.web.mapper;

import com.digiboy.platform.user.dto.UserDTO;
import com.digiboy.platform.user.generated.v1.model.CreateUserRequest;
import com.digiboy.platform.user.generated.v1.model.CreateUserResponse;
import com.digiboy.platform.user.generated.v1.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = EncryptedPasswordMapper.class)
public interface UserModelMapper {
    @Mapping(target = "username", source = "email")
    @Mapping(target = "encryptedPassword", source = "password", qualifiedBy = EncodedMapping.class)
    UserDTO map(CreateUserRequest request);

    CreateUserResponse map(UserDTO user);

    User toUser(UserDTO dto);
}
