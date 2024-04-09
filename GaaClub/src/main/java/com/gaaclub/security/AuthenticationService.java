package com.gaaclub.security;

import com.gaaclub.dto.AuthenticationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    public  String getToken (AuthenticationDTO authenticationDTO);
    public String validateToken(String token);

}
