package com.myblogproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblogproject.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {

}
