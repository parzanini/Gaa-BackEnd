package com.gaaclub.security;

import com.gaaclub.model.Members;
import com.gaaclub.repository.MembersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final AuthenticationService authenticationService;
    private final MembersRepository membersRepository;

    @Autowired
    public SecurityFilter(AuthenticationService authenticationService, MembersRepository membersRepository) {
        this.authenticationService = authenticationService;
        this.membersRepository = membersRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            String login = authenticationService.validateToken(token);
            Members members = membersRepository.findByEmail(login);
            var authentication = new UsernamePasswordAuthenticationToken(members, null, members.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null ) return null;
        return authHeader.replace("Bearer ", "");

    }
}
