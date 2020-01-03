package com.example.justa.demo.service.imp;

import com.example.justa.demo.exception.CustomException;
import com.example.justa.demo.model.Address;
import com.example.justa.demo.model.Provider;
import com.example.justa.demo.model.Telephone;
import com.example.justa.demo.model.dto.ProviderDTO;
import com.example.justa.demo.repository.IProviderRepository;
import com.example.justa.demo.service.IProviderService;
import com.example.justa.demo.util.Constants;
import com.example.justa.demo.util.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements IProviderService {

    @Autowired
    IProviderRepository providerRepository;

    @Autowired
    AddressServiceImpl addressService;

    @Autowired
    TelephoneServiceImpl telephoneService;

    @Override
    public Provider insertProvider(ProviderDTO providerDTO) {
        List<Address> listAddresss = new ArrayList<>();
        List<Telephone> listTelephones = new ArrayList<>();

        Address address = new ModelMapper().map(addressService.returnDataAddress(providerDTO.getCep()), Address.class);
        Telephone telephone = new ModelMapper().map(telephoneService.returnTelephone(providerDTO.getTelephone()), Telephone.class);

        listAddresss.add(address);
        listTelephones.add(telephone);

        Provider provider = new ModelMapper().map(providerDTO, Provider.class);
        provider.setAddress(listAddresss);
        provider.setTelephones(listTelephones);

        return providerRepository.save(provider);
    }

    @Override
    public Provider alterProvider(Provider provider, Long id) {
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        Optional p = providerRepository.findById(id);

        if(p.isPresent()){
            provider.setId(id);
            return providerRepository.save(provider);
        }else{
            throw new CustomException(Constants.PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public Page<Provider> getAllProviders(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    @Override
    public Response deleteById(Long id) {
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        Optional provider = providerRepository.findById(id);

        if(provider.isPresent()){
            providerRepository.deleteById(id);
            return new Response("sucesso", Constants.DELETED_SUCCESSFUL);
        }else{
            throw new CustomException(Constants.PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public Provider findById(Long id) {
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        return providerRepository.findById(id).get();
    }
}