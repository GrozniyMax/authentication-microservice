package com.maxim.securitytinyservice.dto.mapper;

import com.maxim.securitytinyservice.database.dao.Authority;
import com.maxim.securitytinyservice.database.dao.User;
import com.maxim.securitytinyservice.database.dao.UserInfo;
import com.maxim.securitytinyservice.dto.UserInformationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "info.email", target = "email")
    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "authorityToString")
    UserInformationDto userToUserDto(User user);

    @Named("authorityToString")
    default String authorityToString(Authority authority) {
        return authority.getAuthority();
    }
}
