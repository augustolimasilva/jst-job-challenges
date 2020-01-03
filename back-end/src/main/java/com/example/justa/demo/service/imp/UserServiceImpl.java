package com.example.justa.demo.service.imp;

import com.example.justa.demo.exception.CustomException;
import com.example.justa.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.justa.demo.repository.IUserRepository;
import com.example.justa.demo.service.IUserService;
import com.example.justa.demo.util.Constants;
import com.example.justa.demo.util.Response;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    /**
     * @Author augusto.silva
     * Método para inclusão de um novo usuário
     * @param user
     * @return
     */
    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
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
        return (Page<User>) userRepository.findAll(pageable);
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
            return new Response("suceso", "Usuário excluído com sucesso.");
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
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        return userRepository.findById(id).get();
    }
}