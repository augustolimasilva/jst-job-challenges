package com.example.justa.demo.controller;

import com.example.justa.demo.model.User;
import com.example.justa.demo.model.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.justa.demo.service.IUserService;
import com.example.justa.demo.util.Response;
import java.net.URI;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody @Valid UserDTO userDTO){
        User user = new ModelMapper().map(userDTO, User.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userService.insert(user).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public ResponseEntity<User> alter(@RequestBody @Valid UserDTO userDTO, @PathVariable Long id){
        User user = new ModelMapper().map(userDTO, User.class);
        return new ResponseEntity<>(userService.alter(user, id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READING')")
    public ResponseEntity<Page<User>> getAll(Pageable pageable){
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_WRITE')")
    public ResponseEntity<Response> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READING')")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
