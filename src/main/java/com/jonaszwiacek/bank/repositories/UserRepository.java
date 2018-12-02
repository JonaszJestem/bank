package com.jonaszwiacek.bank.repositories;

import com.jonaszwiacek.bank.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String resetToken);
}
