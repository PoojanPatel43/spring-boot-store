package com.poojanpatel.store.mappers;

import com.poojanpatel.store.dtos.RegisterUserRequest;
import com.poojanpatel.store.dtos.UpdateUserRequest;
import com.poojanpatel.store.dtos.UserDto;
import com.poojanpatel.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void updateEntity(UpdateUserRequest request, @MappingTarget User entity);

}
