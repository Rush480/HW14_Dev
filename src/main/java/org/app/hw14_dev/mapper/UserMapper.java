package org.app.hw14_dev.mapper;

import org.app.hw14_dev.model.User;
import org.app.hw14_dev.model.dto.request.UserRequest;
import org.app.hw14_dev.model.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
    User toUser(UserRequest userRequest);
}
