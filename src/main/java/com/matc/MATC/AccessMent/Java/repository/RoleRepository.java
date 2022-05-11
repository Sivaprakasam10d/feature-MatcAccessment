package com.matc.MATC.AccessMent.Java.repository;

import com.matc.MATC.AccessMent.Java.enuM.ERole;
import com.matc.MATC.AccessMent.Java.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}