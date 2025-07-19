package com.boot.start.friend_finder.repository.jwt;

import com.boot.start.friend_finder.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByCode(String code);
}
