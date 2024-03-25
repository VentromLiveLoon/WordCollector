package edu.csmz.ventrom.wordcollector.controller;

import edu.csmz.ventrom.wordcollector.models.User;
import edu.csmz.ventrom.wordcollector.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/user/login")
    public String userLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
//        Check user is exists
        String s = userService.userLogin(username, password);
        if (!s.equals("ok")){
            return s;
        }
//        Set user session and cookie to the sever and client
        HttpSession session = request.getSession();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        session.setAttribute(username,user);
        session.setMaxInactiveInterval(2592000);
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(2592000);
        cookie.setPath("/");
        response.addCookie(cookie);
        return s;
    }
    @PostMapping("/user/register")
    public String userRegister(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword
    ){
       return userService.userRegister(username,password,confirmPassword);
    }
}
