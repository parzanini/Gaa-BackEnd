package com.gaaclub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations{

    private final SecurityFilter securityFilter;

    @Autowired
    public SecurityConfigurations(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/events").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/matches").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/members").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/events/{id}").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/matches/{id}").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/members/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/events").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/addmatch").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/addmember").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/updateevent/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/updatematch/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/updatemember/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/deleteevent/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/deletematch/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/deletemember/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/events/{id}/matches").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/keepServerRunning").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

@Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    return authenticationConfiguration.getAuthenticationManager();
    }
}
