package com.boot.start.friend_finder.service.Impl;

import com.boot.start.friend_finder.config.jwt.TokenHandler;
import com.boot.start.friend_finder.mapper.UserMapper;
import com.boot.start.friend_finder.model.Role;
import com.boot.start.friend_finder.model.User;
import com.boot.start.friend_finder.repository.jwt.RoleRepository;
import com.boot.start.friend_finder.repository.jwt.UserRepository;
import com.boot.start.friend_finder.service.dto.jwt.UserDto;
import com.boot.start.friend_finder.service.jwt.UserService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(UserDto userDto) throws SystemException {

        if (userDto.getId() != null) {
            throw new SystemException("id must be null");
        }

        User userExsits = userRepository.findByEmail(userDto.getEmail());
        if (userExsits != null) {
            throw new RuntimeException("error.clientExist");
        }

        User user = UserMapper.user_Mapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByCode("ROLE_USER");
        if (role == null) {
            throw new SystemException("role not exist");
        }
        List<Role> roles = List.of(role);

        user.setRoles(roles);

        userRepository.save(user);

    }

    @Override
    public List<UserDto> getUsers() throws SystemException {

            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new RuntimeException("error.users");
            }else {
               List<UserDto> userDtos = UserMapper.user_Mapper.toDtoList(users);
               return userDtos;
            }
    }

    @Override
    public UserDto getUserById(Long id) throws SystemException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("error.user");
        }
        return UserMapper.user_Mapper.toDto(user.get());
    }

    @Override
    public void  updateUser(UserDto userDto) throws SystemException {
       if (userDto.getId() == null) {
           throw new SystemException("User ID must not be null for update.");
       }

       Optional<User> user = userRepository.findById(userDto.getId());
       if (!user.isPresent()) {
           throw new RuntimeException("error.user");
       }

       User userExsits = user.get();
       userExsits.setEmail(userDto.getEmail());
       userExsits.setPassword(userDto.getPassword());
       userExsits.setUsername(userDto.getUsername());
       userRepository.save(userExsits);
    }

    @Override
    public void deleteUser(Long id) throws SystemException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("error.user");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserbyEmail(String email) throws SystemException {
        User user = userRepository.findByEmail(email);

        if (user == null) {

            throw new RuntimeException("error.invalid.email");
        }
        return user;
    }

    @Override
    public User checkUserExistByToken(String token) throws SystemException {
        String email = tokenHandler.getSubject(token);

        if (Objects.isNull(email)) {
            return null;
        }
        return  userRepository.findByEmail(email);
    }
}
