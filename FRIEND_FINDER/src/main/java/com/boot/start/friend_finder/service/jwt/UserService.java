package com.boot.start.friend_finder.service.jwt;

import com.boot.start.friend_finder.model.User;
import com.boot.start.friend_finder.service.dto.jwt.UserDto;
import jakarta.transaction.SystemException;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto) throws SystemException;
    List<UserDto> getUsers() throws SystemException;
    UserDto getUserById(Long id) throws SystemException;
    void updateUser(UserDto userDto) throws SystemException;
    void deleteUser(Long id) throws SystemException;
    User getUserbyEmail(String email) throws SystemException;
    User checkUserExistByToken(String token) throws SystemException;
}
