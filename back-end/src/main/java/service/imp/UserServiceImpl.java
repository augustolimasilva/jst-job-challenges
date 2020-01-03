package service.imp;

import exception.CustomException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;
import util.Constants;
import util.Response;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User alterUser(User user, Long id) {
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        Optional<User> u = userRepository.findById(id);

        if(u.isPresent()){
            return userRepository.save(user);
        }else{
            throw new CustomException(Constants.USER_NOT_FOUND);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

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

    @Override
    public User findById(Long id) {
        if(Objects.isNull(id)){
            throw new CustomException(Constants.ID_REQUIRED);
        }

        return userRepository.findById(id).get();
    }
}
