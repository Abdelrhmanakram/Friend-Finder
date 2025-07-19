package com.boot.start.friend_finder.mapper;

import com.boot.start.friend_finder.model.Role;
import com.boot.start.friend_finder.service.dto.jwt.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {

    RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);

    Role toEntity(RoleDto roleDto);
    RoleDto toDto(Role role);

    List<Role> toEntityList(List<RoleDto> roleDtos);
    List<RoleDto> toDtoList(List<Role>  roles);
}
