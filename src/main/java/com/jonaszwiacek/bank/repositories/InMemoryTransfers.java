package com.jonaszwiacek.bank.repositories;

import com.jonaszwiacek.bank.models.Transfer;
import org.springframework.data.repository.CrudRepository;

public interface InMemoryTransfers extends CrudRepository<Transfer, Long> {
    Transfer findByEmailOrUsername(String email, String username);

    void deleteByUsernameOrEmail(String username, String email);
}
