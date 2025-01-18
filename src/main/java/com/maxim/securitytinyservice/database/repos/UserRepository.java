package com.maxim.securitytinyservice.database.repos;

import com.maxim.securitytinyservice.database.dao.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
