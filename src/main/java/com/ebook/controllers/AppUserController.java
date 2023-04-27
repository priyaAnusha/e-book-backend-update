package com.ebook.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebook.dto.LoginDto;
import com.ebook.dto.RegisterDto;
import com.ebook.exceptions.AlreadyExsistException;
import com.ebook.exceptions.InvalidInputException;
import com.ebook.exceptions.InvalidUserException;
import com.ebook.serviceImpl.AppUserServiceImpl;

@RestController
@RequestMapping("/Login")
public class AppUserController {
	@Autowired
    private AppUserServiceImpl appUserServiceImpl;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterDto registerDTO) throws AlreadyExsistException  {
        return appUserServiceImpl.register(registerDTO);
    }
    
    @GetMapping("/")
    public LoginDto login(@RequestParam String email,@RequestParam String password ) throws InvalidInputException{
        return appUserServiceImpl.login(email,password);

    }

    @PutMapping("/resetpassword")
    public String resetPassword(@RequestParam String email,@RequestParam String password ,@Valid @RequestParam String newPassword) throws InvalidInputException {
        return appUserServiceImpl.resetPassword(email,password,newPassword);
    }
    @GetMapping("/checkUser/{username}")
    public String checkAvailability(@PathVariable String username) throws InvalidUserException {
    	return appUserServiceImpl.checkAvailability(username);
    }
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String to) throws InvalidInputException {
        return appUserServiceImpl.sendEmail(to);
    }
    @DeleteMapping("/verify")
    public String Verifyotp(@RequestParam String email,@RequestParam String id) throws InvalidInputException {
        return appUserServiceImpl.VerifyOtp(email, id);

    }
    @PutMapping("/updatePassword")
    public String ChangePassword(@RequestParam String email ,@RequestParam String Password) throws InvalidInputException {
        return appUserServiceImpl.ChangePassword(email, Password);

    }
    @DeleteMapping("/cancel")
    public String Cancel(@RequestParam String email) throws InvalidInputException {
        return appUserServiceImpl.Cancel(email);

    }

}
