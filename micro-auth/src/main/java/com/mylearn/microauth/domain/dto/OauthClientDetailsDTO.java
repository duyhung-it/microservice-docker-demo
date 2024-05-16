package com.mylearn.microauth.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OauthClientDetailsDTO {
    private String aud;
    private Integer userId;
    private String userName;
    private String scope;
    private Integer exp;
    private String authorities;
    private String jti;
    private String clientId;
    private String clientSecret;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
