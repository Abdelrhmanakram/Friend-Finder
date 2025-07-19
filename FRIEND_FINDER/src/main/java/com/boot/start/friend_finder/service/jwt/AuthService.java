package com.boot.start.friend_finder.service.jwt;

import com.boot.start.friend_finder.service.dto.jwt.TokenDto;
import com.boot.start.friend_finder.service.dto.jwt.UserLoginDto;
import jakarta.transaction.SystemException;

public interface AuthService {
    TokenDto login(UserLoginDto userLoginDto) throws SystemException;
}
