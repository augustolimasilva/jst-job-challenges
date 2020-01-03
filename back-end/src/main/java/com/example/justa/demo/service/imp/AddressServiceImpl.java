package com.example.justa.demo.service.imp;

import com.example.justa.demo.consumer.ViaCepClient;
import com.example.justa.demo.model.dto.AddressDTO;
import com.example.justa.demo.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    ViaCepClient viaCepClient;

    @Override
    public AddressDTO returnDataAddress(String cep) {
        return viaCepClient.getAddress(cep);
    }
}
