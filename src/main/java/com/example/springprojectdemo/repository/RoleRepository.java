package com.example.springprojectdemo.repository;

import com.example.springprojectdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findRoleByRole(String role);
}
