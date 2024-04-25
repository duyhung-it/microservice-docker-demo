package com.mylearn.microauth.service;

import com.mylearn.microauth.domain.User;
import com.mylearn.microauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceCustom implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndIsEnabled(username,Boolean.TRUE);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("Username not found:" + username);
    }
}
