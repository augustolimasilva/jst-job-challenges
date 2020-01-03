package com.example.justa.demo.consumer;

import com.example.justa.demo.model.dto.TelephoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://apilayer.net/api", name = "apilayer")
public interface ApilayerClient {

    @GetMapping("/validate?acess_key={token}&numbbbber={numero}")
    TelephoneDTO getTelephoneValid(@PathVariable(value = "token") String token, @PathVariable(value = "numero") String numero);
}
