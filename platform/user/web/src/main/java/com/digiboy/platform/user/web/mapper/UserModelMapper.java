package com.digiboy.platform.user.web.mapper;

import com.digiboy.platform.user.dto.UserDTO;
import com.digiboy.platform.user.generated.v1.model.CreateUserRequest;
import com.digiboy.platform.user.generated.v1.model.CreateUserResponse;
import com.digiboy.platform.user.generated.v1.model.User;
import com.digiboy.platform.user.generated.v1.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserModelMapper {
    User toUser(UserDTO dto);

    List<User> map(List<UserDTO> users);
}
