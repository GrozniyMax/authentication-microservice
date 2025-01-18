package com.maxim.securitytinyservice.database.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@Table("additional_information")
public class UserInfo {

    String email;
}
