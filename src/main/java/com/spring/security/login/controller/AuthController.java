package com.spring.security.login.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.security.login.dto.UserDto;
import com.spring.security.login.entity.User;
import com.spring.security.login.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private UserService userService;
    


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler methode to handle home page
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler to handle the login 

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //handler methode to handle registration form

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";

    }

    // handler methode to handle form submition
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                                 BindingResult result,
                                 Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", "there is already a account exist");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";

    }

    // handler method to handle list of users

    @GetMapping("/users")
    public String listOfUser(Model model){

        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";

    }
    
}
