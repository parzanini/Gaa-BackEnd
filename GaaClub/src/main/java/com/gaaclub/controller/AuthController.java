package com.gaaclub.controller;

import com.gaaclub.dto.AuthenticationDTO;
import com.gaaclub.dto.LoginResponseDTO;
import com.gaaclub.repository.MembersRepository;
import com.gaaclub.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final MembersRepository memberRepository;

    @Autowired
    public AuthController(AuthenticationService authenticationService, AuthenticationManager authenticationManager, MembersRepository memberRepository){
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Validated AuthenticationDTO data){
        var userToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        authenticationManager.authenticate(userToken);
        var token = authenticationService.getToken(data);
        var name = memberRepository.findByEmail(data.email()).getName();
        var role = memberRepository.findByEmail(data.email()).getRole();
        return ResponseEntity.ok(new LoginResponseDTO(token,role,name));
    }
}
