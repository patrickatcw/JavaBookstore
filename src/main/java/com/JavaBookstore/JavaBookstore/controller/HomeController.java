package com.JavaBookstore.JavaBookstore.controller;

import com.JavaBookstore.JavaBookstore.domain.security.PasswordResetToken;
import com.JavaBookstore.JavaBookstore.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class HomeController {

    @Autowired UserService userService;

    @RequestMapping("/")
    public String index(){
      return  "index";
    }

    /*@RequestMapping("/myAccount")
    public String myAccount(){
        return  "myAccount";
    }*/

    //added while working in securityconfig branch, and commented out /myAccount
    //in conjunction with myAccount.html changes, which noted in comments
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    /*@RequestMapping("/forgetPassword")
    public String forgetPassword(Model model) {
        model.addAttribute("classActiveForgetPassword", true);
        return "myAccount";
    }*/

    //modified while working in pswrdrstkn branch
    @RequestMapping("/forgetPassword")
    public String forgetPassword(
            Model model) {


        model.addAttribute("classActiveForgetPassword", true);
        return "myAccount";
    }

    @RequestMapping("/newUser")
    public String newUser(
            Locale locale,
            @RequestParam("token") String token,
            Model model) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);//create new interface UserService
        model.addAttribute("classActiveNewUser", true);
        return "myAccount";
    }

}
