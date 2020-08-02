package ru.stuyan.converter.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.stuyan.converter.dto.UserDto;
import ru.stuyan.converter.entity.SecurityUser;

public interface UserService extends UserDetailsService {

    boolean isUserExists(String login);

    void saveUser(UserDto userDto);

    SecurityUser getCurrentUser();

}
