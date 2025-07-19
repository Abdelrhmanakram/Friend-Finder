package com.boot.start.friend_finder.service.Impl;

import com.boot.start.friend_finder.config.jwt.TokenHandler;
import com.boot.start.friend_finder.model.User;
import com.boot.start.friend_finder.service.dto.jwt.TokenDto;
import com.boot.start.friend_finder.service.dto.jwt.UserLoginDto;
import com.boot.start.friend_finder.service.jwt.AuthService;
import com.boot.start.friend_finder.service.jwt.UserService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto login(UserLoginDto userLoginDto) throws SystemException {
        User user = userService.getUserbyEmail(userLoginDto.getEmail());

        if (!passwordEncoder.matches(userLoginDto.getPassword(),user.getPassword())) {
            throw new BadCredentialsException("error.userNotExist");
        }

        return new TokenDto(tokenHandler.createToken(user));
    }
}
