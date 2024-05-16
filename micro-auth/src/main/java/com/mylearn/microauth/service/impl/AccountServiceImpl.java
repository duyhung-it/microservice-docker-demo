package com.mylearn.microauth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearn.microauth.domain.User;
import com.mylearn.microauth.domain.dto.JwtAuthenticationDTO;
import com.mylearn.microauth.domain.dto.OauthClientDetailsDTO;
import com.mylearn.microauth.repository.UserRepository;
import com.mylearn.microauth.security.jwt.TokenProvider;
import com.mylearn.microauth.service.AccountService;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private Signer signer;
    private final OauthClientDetailsDTO oauthClientDetailsDTO =
            OauthClientDetailsDTO.builder().clientId("microservice-docker-demo").aud("service,manage")
                    .scope("read,write").accessTokenValidity(10800).refreshTokenValidity(2592000)
                    .build();

    @Override
    public JwtAuthenticationDTO login(String username, String password) throws NoSuchAlgorithmException {
        JwtAuthenticationDTO jwtAuthenticationDTO = new JwtAuthenticationDTO();
        User user = userRepository.findByUsernameAndIsEnabled(username,true).orElse(null);
        
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User name not found");
        }
        //Check password

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }
        generate();
        String role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));
        String accessToken = TokenProvider.getToken(oauthClientDetailsDTO,signer,true,new ObjectMapper(),role);
        String refresh = TokenProvider.getToken(oauthClientDetailsDTO,signer,false,new ObjectMapper(),role);
        jwtAuthenticationDTO.setAccessToken(accessToken);
        jwtAuthenticationDTO.setRefreshToken(refresh);
        jwtAuthenticationDTO.setJti(genJti());
        jwtAuthenticationDTO.setScope(oauthClientDetailsDTO.getScope());
        jwtAuthenticationDTO.setTokenType("Bear");
        jwtAuthenticationDTO.setExpiresIn(oauthClientDetailsDTO.getAccessTokenValidity().toString());
        return jwtAuthenticationDTO;
    }

    public void generate() {
//        KeyPair keyPair = getKeyPair();
//        setKeyPair(keyPair);
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("keypair.jks"),
                "MU9ZFdQyX4Q6".toCharArray());
        setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2-server-jwt"));
    }

    public void setKeyPair(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        Assert.state(privateKey instanceof RSAPrivateKey, "KeyPair must be an RSA ");
        this.signer = new RsaSigner((RSAPrivateKey) privateKey);
    }
    public String genJti() throws NoSuchAlgorithmException {
        String jti = UUID.randomUUID().toString();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedJti = digest.digest(jti.getBytes(StandardCharsets.UTF_8));
        jti = java.util.Base64.getEncoder().encodeToString(hashedJti);
        return jti;
    }
}
