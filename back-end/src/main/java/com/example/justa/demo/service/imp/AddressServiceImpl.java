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

    /**
     * @author augusto.silva
     * Método para consumir a api ViaCep, e recuperar os dados do cep informado.
     * @param cep
     * @return
     */
    @Override
    public AddressDTO returnDataAddress(String cep) {
        return viaCepClient.getAddress(cep);
    }
}
