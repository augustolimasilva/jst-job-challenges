package com.example.justa.demo.service.imp;

import com.example.justa.demo.model.Telephone;
import com.example.justa.demo.model.dto.TelephoneDTO;
import com.example.justa.demo.service.ITelephoneService;
import org.springframework.stereotype.Service;

@Service
public class TelephoneServiceImpl implements ITelephoneService {
    @Override
    public TelephoneDTO returnTelephone(String telephone) {
        return null;
    }

    @Override
    public String validateNumberTelephone(String telephone) {
        return null;
    }

    @Override
    public Telephone loadTelephone(TelephoneDTO telephoneDTO) {
        return null;
    }
}
