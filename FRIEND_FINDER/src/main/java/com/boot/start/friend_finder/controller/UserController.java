package com.boot.start.friend_finder.controller;

import com.boot.start.friend_finder.service.dto.jwt.TokenDto;
import com.boot.start.friend_finder.service.dto.jwt.UserDto;
import com.boot.start.friend_finder.service.dto.jwt.UserLoginDto;
import com.boot.start.friend_finder.service.jwt.AuthService;
import com.boot.start.friend_finder.service.jwt.UserService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/create-user")
    ResponseEntity<Void> createUser(@RequestBody @Validated UserDto userDto) throws SystemException {
        userService.saveUser(userDto);
        return  ResponseEntity.created(URI.create("/user/addUserWithRole")).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<UserDto>> getAllUsers() throws SystemException {
//        try {
            List<UserDto> users = userService.getUsers();
            return ResponseEntity.ok(users);
//        } catch (SystemException e) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                    .body(Collections.emptyList());
//        }
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws SystemException {
//        try {
            UserDto userDto = userService.getUserById(id);
            return ResponseEntity.ok(userDto);
//        }catch (SystemException e) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody @Validated UserDto userDto) throws SystemException {
//        try{
            userDto.setId(id);
            userService.updateUser(userDto);
            return ResponseEntity.ok().build();
//        }catch (SystemException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id) throws SystemException {
//        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
//        }catch (SystemException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
    }

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody UserLoginDto userLoginDto) throws SystemException {
        return ResponseEntity.ok(authService.login(userLoginDto));
    }
}
