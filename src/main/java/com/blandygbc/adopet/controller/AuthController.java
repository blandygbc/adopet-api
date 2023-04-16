package com.blandygbc.adopet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandygbc.adopet.domain.user.User;
import com.blandygbc.adopet.domain.user.UserLoginModel;
import com.blandygbc.adopet.infra.security.TokenJWTDTO;
import com.blandygbc.adopet.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTDTO> doLogin(@RequestBody @Valid UserLoginModel loginModel) {
        var userPassAuthToken = new UsernamePasswordAuthenticationToken(loginModel.email(), loginModel.password());
        var authentication = manager.authenticate(userPassAuthToken);
        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}
