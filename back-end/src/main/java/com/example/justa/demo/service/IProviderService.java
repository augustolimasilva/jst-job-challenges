package com.example.justa.demo.service;

import com.example.justa.demo.model.Provider;
import com.example.justa.demo.model.dto.ProviderDTO;
import com.example.justa.demo.util.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProviderService {
    Provider insertProvider(ProviderDTO providerDTO);

    Provider alterProvider(Provider provider, Long id);

    Page<Provider> getAllProviders(Pageable pageable);

    Response deleteById(Long id);

    Provider findById(Long id);
}
