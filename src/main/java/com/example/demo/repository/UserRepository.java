package com.example.demo.repository;

import com.example.demo.model.Userdetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userdetail, Long> {
    Userdetail findByUsername(String username);
}
