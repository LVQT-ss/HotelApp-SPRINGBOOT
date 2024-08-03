package com.thinhle.lakesidehotel.service;

import com.thinhle.lakesidehotel.model.Role;
import com.thinhle.lakesidehotel.model.User;
import com.thinhle.lakesidehotel.repository.RoleRepository;
import com.thinhle.lakesidehotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Override
    public List<Role> getRoles() {
        return List.of();
    }

    @Override
    public Role createRole(Role theRole) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        return null;
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        return null;
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        return null;
    }
}
