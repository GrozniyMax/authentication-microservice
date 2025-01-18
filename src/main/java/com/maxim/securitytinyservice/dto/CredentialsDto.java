package com.maxim.securitytinyservice.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CredentialsDto {

    @NonNull
    private String login;

    @NonNull
    private String password;

}
