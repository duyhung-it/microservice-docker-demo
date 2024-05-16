package com.mylearn.microauth.service;

import com.mylearn.microauth.controller.vim.LoginVim;
import com.mylearn.microauth.domain.dto.JwtAuthenticationDTO;

import java.security.NoSuchAlgorithmException;

public interface AccountService {
    JwtAuthenticationDTO login(String username, String password) throws NoSuchAlgorithmException;
}
