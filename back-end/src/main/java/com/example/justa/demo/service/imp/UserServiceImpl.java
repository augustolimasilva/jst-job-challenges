package com.example.justa.demo.service.imp;

import com.example.justa.demo.exception.CustomException;
import com.example.justa.demo.model.User;
import com.example.justa.demo.model.UserSystem;
import com.example.justa.demo.repository.IUserRepository;
import com.example.justa.demo.service.IUserService;
import com.example.justa.demo.util.Constants;
import com.example.justa.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @Author augusto.silva
     * Método para inclusão de um novo usuário
     * @param user
     * @return
     */
    @Override
    public User insertUser(User user) {
    //    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      //  return userRepository.save(user);
        return null;
    }

    /**
     * @Author augusto.silva
     * Método para alteração de usuário
     * @param user
     * @param id
     * @return
     */
    @Override
    public User alterUser(User user, Long id) {
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        Optional<User> u = userRepository.findById(id);

        if(u.isPresent()){
            user.setId(id);
            return userRepository.save(user);
        }else{
            throw new CustomException(Constants.USER_NOT_FOUND);
        }
    }

    /**
     * @Author augusto.silva
     * Método para retornar todos os usuários cadastrados
     * @param pageable
     * @return
     */
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * @Author augusto.silva
     * Método para deletar um usuário
     * @param id
     * @return
     */
    @Override
    public Response deleteById(Long id) {
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        Optional<User> u = userRepository.findById(id);

        if(u.isPresent()){
            userRepository.deleteById(id);
            return new Response("sucesso", Constants.DELETED_SUCCESSFUL);
        }else{
            throw new CustomException(Constants.USER_NOT_FOUND);
        }
    }

    /**
     * @Author augusto.silva
     * Método para retornar um usuário pelo Id.
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return user.get();
        }else{
            throw new CustomException(Constants.USER_NOT_FOUND);
        }
    }

    /***
     * @Author augusto.silva
     * Método para carregar o usuário pelo username. Será utilizado para autenticar o usuário.
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return new UserSystem(user.get(), getRoles(user.get()));
        }else{
            throw new CustomException(Constants.USER_NOT_FOUND);
        }
    }

    /***
     * @Author augusto.silva
     * Método para recuperar as permissões do usuário
     * @param user
     * @return
     */
    private Collection<SimpleGrantedAuthority> getRoles(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
        return authorities;
    }
}