package com.maxim.securitytinyservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Builder
@Data
@Setter
public class UserInformationDto {

    @NonNull
    private String username;

    @NonNull
    private Collection<String> authorities;

    private String email;
}
