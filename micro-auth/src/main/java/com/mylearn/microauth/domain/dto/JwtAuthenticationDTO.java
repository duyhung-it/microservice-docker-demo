package com.mylearn.microauth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String expiresIn;
    private String scope;
    private String jti;
}
