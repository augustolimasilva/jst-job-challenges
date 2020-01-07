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

    /**
     * @author augusto.silva
     * Método para consumir a api da Apilayer, que vai validar se o telefone informado está válido.
     * @param telephone
     * @return
     */
    @Override
    public TelephoneDTO returnTelephone(String telephone) {
        return apilayerClient.getTelephoneValid(tokenAcessApi, this.validTelephoneCel(telephone));
    }

    /**
     * @author Augusto.silva
     * Método para verificar se o telefone já está com o +55.
     * @param telephone
     * @return
     */
    private String validTelephoneCel(String telephone){
        return telephone.contains("+55") ? telephone : "+55" + telephone;
    }
}
