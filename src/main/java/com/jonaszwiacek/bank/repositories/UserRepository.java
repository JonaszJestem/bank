package com.jonaszwiacek.bank.repositories;

import com.jonaszwiacek.bank.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
