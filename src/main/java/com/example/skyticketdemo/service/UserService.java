package com.example.skyticketdemo.service;

import com.example.skyticketdemo.dto.UserCreateDTO;
import com.example.skyticketdemo.dto.UserDTO;
import com.example.skyticketdemo.entity.Role;
import com.example.skyticketdemo.entity.User;
import com.example.skyticketdemo.exceptions.BadCredentialsException;
import com.example.skyticketdemo.exceptions.EmailNotAvailableException;
import com.example.skyticketdemo.mapper.UserMapper;
import com.example.skyticketdemo.repository.impl.UserRepositoryImpl;
import com.example.skyticketdemo.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {
    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepositoryImpl userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void register(UserCreateDTO user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailNotAvailableException("Пользователь с таким email уже существует");
        }

        if (user.getFirstName() == null || user.getFirstName().isEmpty()
                || user.getLastName() == null || user.getLastName().isEmpty()
                || user.getEmail() == null || user.getEmail().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Все поля должны быть заполнены");
        }

        user.setPassword(PasswordUtil.hash(user.getPassword()));
        User entityCreate = userMapper.toEntity(user);
        if (user.getEmail().contains("_admin@")) {
            entityCreate.setRole(Role.ADMIN);
        } else {
            entityCreate.setRole(Role.USER);
        }
        log.info("Новая сущность:" + entityCreate);
        userRepository.save(entityCreate);
    }

    public UserDTO authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && PasswordUtil.verify(password, user.getPassword())) {
            System.out.println("Authenticating User: " + user);
            UserDTO userDTO = userMapper.toDto(user);
            System.out.println("Mapped UserDTO: " + userDTO);
            return userDTO;
        }
        else throw new BadCredentialsException("Неверная почта или пароль");
    }

    public void updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id);

        if (existingUser == null) {
            throw new IllegalArgumentException("Пользователь с ID " + id + " не найден.");
        }

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());

        userRepository.update(existingUser);
    }

}
