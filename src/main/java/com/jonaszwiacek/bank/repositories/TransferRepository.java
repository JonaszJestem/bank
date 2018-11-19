package com.jonaszwiacek.bank.repositories;

import com.jonaszwiacek.bank.models.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long> {
    List<Transfer> findByEmail(String email);
}
