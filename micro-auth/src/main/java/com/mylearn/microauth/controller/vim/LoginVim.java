package com.mylearn.microauth.controller.vim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVim {
    private String username;
    private String password;
}
