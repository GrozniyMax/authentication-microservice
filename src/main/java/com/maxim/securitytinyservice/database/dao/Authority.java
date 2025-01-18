package com.maxim.securitytinyservice.database.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

import java.lang.annotation.Target;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("authorities")
@RequiredArgsConstructor
public class Authority implements GrantedAuthority {

    @NonNull
    String authority;
}
