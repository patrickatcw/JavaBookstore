package com.JavaBookstore.JavaBookstore.controller;

import com.JavaBookstore.JavaBookstore.domain.User;
import com.JavaBookstore.JavaBookstore.domain.security.PasswordResetToken;
import com.JavaBookstore.JavaBookstore.domain.security.Role;
import com.JavaBookstore.JavaBookstore.domain.security.UserRole;
import com.JavaBookstore.JavaBookstore.service.UserService;
import com.JavaBookstore.JavaBookstore.service.impl.UserSecurityService;

import com.JavaBookstore.JavaBookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

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
    public String forgetPassword(Model model) {
        model.addAttribute("classActiveForgetPassword", true);
        return "myAccount";
    }

    //added while in pswrdrstkn
    @RequestMapping(value="/newUser", method= RequestMethod.POST)
    public String newUserPost(HttpServletRequest request,
                              @ModelAttribute("email") String userEmail,
                              @ModelAttribute("username") String username,
                              Model model) throws Exception{
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", username);

        if (userService.findByUsername(username) != null) { //need to make findByUserName method in userservice
            model.addAttribute("usernameExists", true);

            return "myAccount";
        }

        if (userService.findByEmail(userEmail) != null) {   //need to make findByEmail method in userservice
            model.addAttribute("email", true);

            return "myAccount";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);    //createUser, make in

    }

    @RequestMapping("/newUser")
    public String newUser(
            Locale locale,
            @RequestParam("token") String token,
            Model model) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);//create new interface UserService

        //authentication, code block below done while working in pswrdrstkn
        //user can be found by a token
        if (passToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";  //done when invalid input
        }

        User user = passToken.getUser();
        String username = user.getUsername();

        //sets current login session to that user, codeblock below

        //user details are obtained by user security service
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);  //must be autowired

        //authentication using user details
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        //sets authentication to the current user
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /*model.addAttribute("classActiveNewUser", true);
        return "myAccount";*/

        //change from above in pswrdrstkn branch
        model.addAttribute("classActiveEdit", true);
        return "myProfile"; //need to add myProfile html file template next

    }

}
