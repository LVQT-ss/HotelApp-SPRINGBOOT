package com.thinhle.lakesidehotel.service;

import com.thinhle.lakesidehotel.model.User;
import com.thinhle.lakesidehotel.repository.UserRepository;

import java.util.List;

public class UserService implements IUserService {

    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return null;
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
