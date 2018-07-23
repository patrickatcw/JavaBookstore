package com.JavaBookstore.JavaBookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

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

    @RequestMapping("/forgetPassword")
    public String forgetPassword(Model model) {
        model.addAttribute("classActiveForgetPassword", true);
        return "myAccount";
    }

    @RequestMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("classActiveNewUser", true);
        return "myAccount";
    }

}
