package com.daoqonq1408.workshopbesql.controller;

import com.daoqonq1408.workshopbesql.dto.LoginDTO;
import com.daoqonq1408.workshopbesql.dto.request.CreateUserRequestDTO;
import com.daoqonq1408.workshopbesql.dto.response.JwtAuthResponse;
import com.daoqonq1408.workshopbesql.service.UserService;
import com.daoqonq1408.workshopbesql.util.JwtTokenProvider;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(
            @RequestBody LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        userService.addUser(createUserRequestDTO);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }
}
