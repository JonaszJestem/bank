package com.jonaszwiacek.bank.repositories;

import com.jonaszwiacek.bank.models.Transfer;
import org.springframework.data.repository.CrudRepository;

public interface InMemoryTransfers extends CrudRepository<Transfer, Long> {
    Transfer findByEmail(String email);
}
