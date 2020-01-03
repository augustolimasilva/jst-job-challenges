package com.example.justa.demo.service;

import com.example.justa.demo.model.Provider;
import com.example.justa.demo.model.User;
import com.example.justa.demo.util.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProviderService {
    User insertProvider(Provider provider);

    User alterProvider(User user, Long id);

    Page<Provider> getAllProviders(Pageable pageable);

    Response deleteById(Long id);

    User findById(Long id);
}
