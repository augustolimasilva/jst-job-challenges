package com.example.justa.demo.service;

import com.example.justa.demo.model.Telephone;
import com.example.justa.demo.model.dto.TelephoneDTO;

public interface ITelephoneService {

    TelephoneDTO returnTelephone(String telephone);

    String validateNumberTelephone(String telephone);

    Telephone loadTelephone(TelephoneDTO telephoneDTO);
}
