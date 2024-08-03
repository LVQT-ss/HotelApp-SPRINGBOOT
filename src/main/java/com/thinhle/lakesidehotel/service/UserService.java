package com.thinhle.lakesidehotel.service;

import com.thinhle.lakesidehotel.model.User;
import com.thinhle.lakesidehotel.repository.RoleRepository;
import com.thinhle.lakesidehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + "already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public User getUser(String email) {
        return null;
    }
}
