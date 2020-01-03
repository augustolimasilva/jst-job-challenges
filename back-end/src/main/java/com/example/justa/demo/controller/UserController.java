package com.example.justa.demo.controller;

import com.example.justa.demo.model.User;
import com.example.justa.demo.model.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> insertUser(@RequestBody @Valid UserDTO userDTO){
        User user = new ModelMapper().map(userDTO, User.class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userService.insertUser(user).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> alterUser(@RequestBody @Valid UserDTO userDTO, @PathVariable Long id){
        User user = new ModelMapper().map(userDTO, User.class);
        return new ResponseEntity<>(userService.alterUser(user, id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsers(pageable), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
