package com.boot.start.friend_finder.mapper;

import com.boot.start.friend_finder.model.User;
import com.boot.start.friend_finder.service.dto.jwt.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {


    UserMapper user_Mapper = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);
    List<User> toEntityList(List<UserDto> userDtoList);

    @Mapping(target ="roles", ignore = true)
    UserDto toDto(User user);
    List<UserDto> toDtoList(List<User> userList);


}
