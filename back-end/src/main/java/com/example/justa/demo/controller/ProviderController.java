package com.example.justa.demo.controller;

import com.example.justa.demo.model.Provider;
import com.example.justa.demo.model.dto.ProviderDTO;
import com.example.justa.demo.service.IProviderService;
import com.example.justa.demo.util.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    IProviderService providerService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public ResponseEntity<Provider> insert(@RequestBody @Valid ProviderDTO providerDTO){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(providerService.insert(providerDTO).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public ResponseEntity<Provider> alter(@RequestBody @Valid ProviderDTO providerDTO, @PathVariable Long id){
        Provider provider = new ModelMapper().map(providerDTO, Provider.class);
        return new ResponseEntity<>(providerService.alter(provider, id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READING')")
    public ResponseEntity<Page<Provider>> getAll(Pageable pageable){
        return new ResponseEntity<>(providerService.findAll(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public ResponseEntity<Response> delete(@PathVariable Long id){
        return new ResponseEntity<>(providerService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READING')")
    public ResponseEntity<Provider> findById(@PathVariable Long id){
        return new ResponseEntity<>(providerService.findById(id), HttpStatus.OK);
    }
}