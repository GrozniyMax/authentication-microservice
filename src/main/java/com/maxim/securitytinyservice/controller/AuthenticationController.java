package com.maxim.securitytinyservice.controller;

import com.maxim.securitytinyservice.dto.CredentialsDto;
import com.maxim.securitytinyservice.dto.JwtTokenDto;
import com.maxim.securitytinyservice.dto.UserInformationDto;
import com.maxim.securitytinyservice.exc.UserExistsException;
import com.maxim.securitytinyservice.service.JwtTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {


    UserDetailsManager userDetailsManager;
    AuthenticationManager authenticationManager;
    JwtTokenService tokenService;

    @PostMapping("/register")
    public void register(@RequestBody CredentialsDto credentialsDto) {
        if (!userDetailsManager.userExists(credentialsDto.getLogin())) {
            throw new UserExistsException();
        }

        userDetailsManager.createUser(User.builder()
                .username(credentialsDto.getLogin())
                .password(credentialsDto.getPassword())
                .roles("USER")
                .build());
    }


    @PostMapping("/login")
    public JwtTokenDto login(@RequestBody CredentialsDto credentialsDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentialsDto.getLogin(),
                credentialsDto.getPassword()
        ));

        if (!authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User with name %s not found");
        }

        return new JwtTokenDto(tokenService
                .generateToken(userDetailsManager.loadUserByUsername(credentialsDto.getLogin())));
    }


    @GetMapping("/me")
    public UserInformationDto credentialsInfo(@AuthenticationPrincipal UserDetails user) {
        var userDto = UserInformationDto.builder()
                .username(user.getUsername())
                .authorities(user.getAuthorities().stream().map((GrantedAuthority::getAuthority)).toList())
                .build();
        return userDto;
    }

    @ExceptionHandler({AuthenticationCredentialsNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handle(AuthenticationCredentialsNotFoundException e) {
        return "Sing in failed due to %s".formatted(e.getMessage());
    }

    @ExceptionHandler({UserExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(UserExistsException e) {
        return "User already exists";
    }
}
