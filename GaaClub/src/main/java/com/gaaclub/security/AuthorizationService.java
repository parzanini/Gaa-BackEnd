package com.gaaclub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gaaclub.dto.AuthenticationDTO;
import com.gaaclub.model.Members;
import com.gaaclub.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AuthorizationService implements AuthenticationService {

    @Autowired
    private MembersRepository membersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return membersRepository.findByEmail(email);
    }

    @Override
    public String getToken(AuthenticationDTO authenticationDTO) {
        Members member = membersRepository.findByEmail(authenticationDTO.email());
       return generateToken(member);
    }

    public String generateToken (Members member) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret-api-key");
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(member.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error generating token");
        }
    }

    private Instant getExpirationDate() {
        return LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret-api-key");
return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

} catch (JWTVerificationException exception) {
            return "";
        }
    }
}
