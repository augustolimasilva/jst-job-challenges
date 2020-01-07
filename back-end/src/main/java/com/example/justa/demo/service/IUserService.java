package com.example.justa.demo.service;

import com.example.justa.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.justa.demo.util.Response;

public interface IUserService {

    User insert(User user);

    User alter(User user, Long id);

    Page<User> findAll(Pageable pageable);

    Response deleteById(Long id);

    User findById(Long id);
}
