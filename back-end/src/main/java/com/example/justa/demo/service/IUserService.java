package com.example.justa.demo.service;

import com.example.justa.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.justa.demo.util.Response;

public interface IUserService {

    User insertUser(User user);

    User alterUser(User user, Long id);

    Page<User> getAllUsers(Pageable pageable);

    Response deleteById(Long id);

    User findById(Long id);

    User loadUserByUsername(String username);
}
