package com.maxim.securitytinyservice.database.dao;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Getter @Setter
@Table("users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Id
    String username;

    String password;

    @MappedCollection(idColumn = "username", keyColumn = "username")
    Collection<Authority> authorities;

    @MappedCollection(idColumn = "username")
    UserInfo info;

}
