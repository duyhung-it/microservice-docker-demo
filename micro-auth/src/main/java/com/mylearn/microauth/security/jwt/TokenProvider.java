package com.mylearn.microauth.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearn.microauth.config.Constants;
import com.mylearn.microauth.domain.dto.OauthClientDetailsDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;


public class TokenProvider {
    public static String generateAccessToken() {
        // Generate a random access token value (You would normally get this from your OAuth provider)
        String tokenValue = "YOUR_RANDOM_ACCESS_TOKEN_VALUE";

        // Create a new OAuth2AccessToken object
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(tokenValue);

        // Set expiration time in milliseconds (e.g., 1 hour)
        accessToken.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000));

        // Return the access token
        return accessToken.getValue();
    }

    public static String getToken(OauthClientDetailsDTO info, Signer signer, boolean isAccessToken, ObjectMapper objectMapper, String role) throws NoSuchAlgorithmException {
        Map<String, Object> putObjAccessToken = putObj(info, isAccessToken, role);
        try {
            String jsonAccess = objectMapper.writeValueAsString(putObjAccessToken);
            String token = JwtHelper.encode(jsonAccess, signer).getEncoded();
            return token;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Map<String, Object> putObj(OauthClientDetailsDTO dto, boolean isAccessToken,String role) throws NoSuchAlgorithmException {
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put(Constants.AUD, dto.getAud().split(","));
        mapObj.put(Constants.SCOPE, dto.getScope().split(","));
        if (isAccessToken) {
            mapObj.put(Constants.EXP, new Date().getTime() + (long) dto.getAccessTokenValidity() * Constants.S_TO_MS);
        } else {
            mapObj.put(Constants.EXP, new Date().getTime() + (long) dto.getRefreshTokenValidity() * Constants.S_TO_MS);
        }
        mapObj.put(Constants.AUTHORITIES, role);
        mapObj.put(Constants.JTI, genJti());
        mapObj.put(Constants.CLIENT_ID, dto.getClientId());
        return mapObj;
    }

    public static String genJti() throws NoSuchAlgorithmException {
        String jti = UUID.randomUUID().toString();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedJti = digest.digest(jti.getBytes(StandardCharsets.UTF_8));
        jti = java.util.Base64.getEncoder().encodeToString(hashedJti);
        return jti;
    }
}
