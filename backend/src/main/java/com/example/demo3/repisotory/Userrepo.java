package com.example.demo3.repisotory;
import com.example.demo3.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface Userrepo extends JpaRepository<User, Long> {

    void deleteUserById(Long id);

    Optional<User> findUserById(Long id);

    User findUserByEmail(String email);

    User findUserByEmailAndMotdepasse(String email, String password);
}
