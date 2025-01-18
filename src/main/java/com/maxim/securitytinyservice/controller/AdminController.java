package com.maxim.securitytinyservice.controller;

import com.maxim.securitytinyservice.database.repos.UserRepository;
import com.maxim.securitytinyservice.dto.UserInformationDto;
import com.maxim.securitytinyservice.dto.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Controller for admin actions/monitoring
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    UserRepository userRepository;

    UserMapper userMapper;

    @GetMapping("/users-list")
    @PreAuthorize("hasRole(\"admin\")")
    public List<UserInformationDto> userInformationList(@RequestParam("user") List<String> usernameList) {
        return usernameList.stream()
                .distinct()
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(userMapper::userToUserDto)
                .toList();
    }




}
