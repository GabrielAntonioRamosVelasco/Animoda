package com.tienda.animoda.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.animoda.Entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username); 
}