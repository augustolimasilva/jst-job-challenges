package com.example.justa.demo.service.imp;

import com.example.justa.demo.consumer.ApilayerClient;
import com.example.justa.demo.model.dto.TelephoneDTO;
import com.example.justa.demo.service.ITelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TelephoneServiceImpl implements ITelephoneService {

    @Autowired
    ApilayerClient apilayerClient;

    @Value("${token.acess.api}")
    private String tokenAcessApi;

    @Override
    public TelephoneDTO returnTelephone(String telephone) {
        return apilayerClient.getTelephoneValid(tokenAcessApi, this.validTelephoneCel(telephone));
    }

    private String validTelephoneCel(String telephone){
        return telephone.contains("+55") ? telephone : "+55" + telephone;
    }
}
