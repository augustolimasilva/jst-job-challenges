package com.example.justa.demo.service.imp;

import com.example.justa.demo.exception.CustomException;
import com.example.justa.demo.model.Address;
import com.example.justa.demo.model.Provider;
import com.example.justa.demo.model.Telephone;
import com.example.justa.demo.model.dto.AddressDTO;
import com.example.justa.demo.model.dto.ProviderDTO;
import com.example.justa.demo.model.dto.TelephoneDTO;
import com.example.justa.demo.repository.IProviderRepository;
import com.example.justa.demo.service.IProviderService;
import com.example.justa.demo.util.Constants;
import com.example.justa.demo.util.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    /**
     * @author augusto.silva
     * Método para incluir um novo Fornecedor.
     * @param providerDTO
     * @return
     */
    @Override
    public Provider insert(ProviderDTO providerDTO) {
        List<Address> listAddresss = new ArrayList<>();
        List<Telephone> listTelephones = new ArrayList<>();
        Telephone telephone = new Telephone();
        Address address = new Address();

        AddressDTO addressDTO = addressService.returnDataAddress(providerDTO.getCep());

        if(addressDTO.getCep() == null){
            throw new CustomException(Constants.CEP_NOT_VALID);
        }else{
            address.setCep(providerDTO.getCep());
            address.setComplement(addressDTO.getComplemento());
            address.setDistrict(addressDTO.getBairro());
            address.setLocation(addressDTO.getLocalidade());
            address.setUf(addressDTO.getUf());
            address.setPlace(addressDTO.getLogradouro());
        }

        TelephoneDTO telephoneDTO = telephoneService.returnTelephone(providerDTO.getTelephone());

        if(telephoneDTO.getValid()){
            telephone = new ModelMapper().map(telephoneDTO, Telephone.class);
        }else{
            throw new CustomException(Constants.TELEPHONE_NOT_VALID);
        }

        listAddresss.add(address);
        listTelephones.add(telephone);

        Provider provider = new ModelMapper().map(providerDTO, Provider.class);
        provider.setAddress(listAddresss);
        provider.setTelephones(listTelephones);

        return providerRepository.save(provider);
    }

    /**
     * @author augusto.silva
     * Método para alterar um fornecedor.
     * @param provider
     * @param id
     * @return
     */
    @Override
    public Provider alter(Provider provider, Long id) {
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

    /**
     * @author augusto.silva
     * Método para pesquisar os fornecedores cadastrados.
     * @param pageable
     * @return
     */
    @Override
    public Page<Provider> findAll(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    /**
     * @author augusto.silva
     * Método para deletar um fornecedor pelo seu Id.
     * @param id
     * @return
     */
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

    /**
     * @author augusto.silva
     * Método para pesquisar um fornecedor pelo o Id.
     * @param id
     * @return
     */
    @Override
    public Provider findById(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);

        if(provider.isPresent()){
            return provider.get();
        }else{
            throw new CustomException(Constants.PROVIDER_NOT_FOUND);
        }
    }
}