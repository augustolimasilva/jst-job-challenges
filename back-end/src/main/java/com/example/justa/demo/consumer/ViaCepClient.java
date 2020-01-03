package com.example.justa.demo.consumer;

import com.example.justa.demo.model.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepClient {

    @GetMapping("/{ce}/json/")
    AddressDTO getAddress(@PathVariable(value = "cep") String cep);
}
