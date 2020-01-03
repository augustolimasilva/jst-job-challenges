package com.example.justa.demo.service;

import com.example.justa.demo.model.dto.AddressDTO;

public interface IAddressService {
    AddressDTO returnDataAddress(String cep);
}
