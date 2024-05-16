package com.mylearn.microauth.controller;

import com.google.gson.Gson;
import com.mylearn.microauth.controller.vim.LoginVim;
import com.mylearn.microauth.service.AccountService;
import com.mylearn.microutil.ApiController;
import com.mylearn.microutil.RequestUtil;
import com.mylearn.microutil.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController extends ApiController {

    private final AccountService accountService;

    @GetMapping("/hello")
    public String getHello(){
        return "HelloWorld";
    }

    @PostMapping("/login1")
    public ResponseEntity<Object> login(@RequestBody LoginVim loginVim) throws NoSuchAlgorithmException {
        return ok(accountService.login(loginVim.getUsername(),loginVim.getPassword()));
    }
}
