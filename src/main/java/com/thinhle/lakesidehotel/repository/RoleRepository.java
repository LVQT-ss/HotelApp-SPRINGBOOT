package com.thinhle.lakesidehotel.repository;

import com.thinhle.lakesidehotel.model.Role;
import com.thinhle.lakesidehotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String role);


    boolean existsByName(String role);
}
