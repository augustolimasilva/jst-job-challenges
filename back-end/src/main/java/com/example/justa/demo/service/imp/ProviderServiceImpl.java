package com.example.justa.demo.service.imp;

import com.example.justa.demo.model.Provider;
import com.example.justa.demo.model.User;
import com.example.justa.demo.repository.IProviderRepository;
import com.example.justa.demo.service.IProviderService;
import com.example.justa.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements IProviderService {

    @Autowired
    IProviderRepository providerRepository;

    @Override
    public User insertProvider(Provider provider) {
        return null;
    }

    @Override
    public User alterProvider(User user, Long id) {
        return null;
    }

    @Override
    public Page<Provider> getAllProviders(Pageable pageable) {
        return null;
    }

    @Override
    public Response deleteById(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
