package edu.csmz.ventrom.wordcollector.service;

import edu.csmz.ventrom.wordcollector.models.User;
import edu.csmz.ventrom.wordcollector.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String userLogin(String username,String password){

        User user = userRepository.findUserByUsernameAndPassword(username, password);

        if (user==null){
            return "User name or password error!!";
        }
        return "ok";
    }
    public String userRegister(String username,String password,String comfirmPassword){
        if (!password.equals(comfirmPassword)){
            return "Password not equaled!!";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return "ok";
    }
}
