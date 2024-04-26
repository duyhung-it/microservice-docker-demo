package com.mylearn.microauth.controller;

import com.mylearn.microauth.controller.vim.LoginVim;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/hello")
    public String getHello(){
        return "HelloWorld";
    }

    @PostMapping("/login")
    public ResponseEntity login(LoginVim loginVim){
        return new ResponseEntity(HttpStatus.OK);
    }
}
