package ru.stuyan.converter.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.stuyan.converter.dto.UserDto;
import ru.stuyan.converter.repository.UserRepository;
import ru.stuyan.converter.entity.SecurityUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private PasswordEncoder bCryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		var user = userRepository.findByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException("Пользователь с логином " + login + " не найден.");
		}
		return new User(user.getLogin(), user.getPassword(), new HashSet<>());
	}

    @Override
    public boolean isUserExists(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public void saveUser(UserDto userDto) {
	    var user = new SecurityUser();
	    user.setLogin(userDto.getLogin());
        user.setPassword(bCryptEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public SecurityUser getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        var login = user.getUsername();
        return userRepository.findByLogin(login);
    }

}
