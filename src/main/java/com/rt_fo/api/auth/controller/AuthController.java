package com.rt_fo.api.auth.controller;

import com.rt_fo.api.auth.dto.AuthenticatedUserDto;
import com.rt_fo.api.auth.dto.LoginRequest;
import com.rt_fo.api.auth.dto.TokenResponse;
import com.rt_fo.api.security.jwt.JwtProvider;
import com.rt_fo.api.security.jwt.JwtService;
import com.rt_fo.api.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtProvider jwtProvider;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtProvider jwtProvider, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.email(),
                        request.password()
                )
        );

        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/user")
    public ResponseEntity<AuthenticatedUserDto> getAuthenticatedUser(@AuthenticationPrincipal Jwt jwt) {
        User user = jwtService.getUserFromJwt(jwt);

        return ResponseEntity.ok(AuthenticatedUserDto.fromEntity(user));
    }
}
