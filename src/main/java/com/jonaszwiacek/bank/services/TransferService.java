package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.InMemoryTransfer;
import com.jonaszwiacek.bank.models.Transfer;
import com.jonaszwiacek.bank.repositories.InMemoryTransfers;
import com.jonaszwiacek.bank.repositories.TransferRepository;
import com.jonaszwiacek.bank.security.UserPrincipal;
import com.jonaszwiacek.bank.services.exceptions.NoTranfserFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
public class TransferService {
    private TransferRepository transferRepository;
    private InMemoryTransfers inMemoryTransfers;

    public TransferService(TransferRepository transferRepository, InMemoryTransfers inMemoryTransfers) {
        this.transferRepository = transferRepository;
        this.inMemoryTransfers = inMemoryTransfers;

        for (int i = 0; i < 10; i++) {
            Transfer transfer = new Transfer();
            transfer.setAmount(String.valueOf(new Random().nextInt(1000)));
            transfer.setEmail("email@email.com");
            transfer.setTitle("title");
            transfer.setUsername("login");
            transferRepository.save(transfer);
        }
    }

    @Transactional
    public String transfer(UserPrincipal currentUser) {
        InMemoryTransfer confirmedTransfer = inMemoryTransfers.findByEmailOrUsername(currentUser.getEmail(), currentUser.getUsername());
        if (confirmedTransfer != null) {
            inMemoryTransfers.deleteByUsernameOrEmail(confirmedTransfer.getUsername(), confirmedTransfer.getEmail());

            Transfer transfer = new Transfer();
            transfer.setAmount(confirmedTransfer.getAmount());
            transfer.setTitle(confirmedTransfer.getTitle());
            transfer.setUsername(confirmedTransfer.getUsername());
            transfer.setEmail(confirmedTransfer.getEmail());
            transferRepository.save(transfer);
            return "{\"message\": \"OK.\"}";
        } else {
            throw new NoTranfserFoundException();
        }
    }

    @Transactional
    public void saveTemporary(UserPrincipal currentUser, InMemoryTransfer transfer) {
        String username = currentUser.getUsername();
        String email = currentUser.getEmail();
        transfer.setUsername(username);
        transfer.setEmail(email);

        inMemoryTransfers.deleteByUsernameOrEmail(username, email);
        inMemoryTransfers.save(transfer);
    }

    public List<Transfer> getHistory(UserPrincipal currentUser) {
        Iterable<Transfer> history = transferRepository.findAllByEmailOrUsername(currentUser.getEmail(), currentUser.getUsername());
        List<Transfer> result = new ArrayList<>();
        history.forEach(result::add);
        System.out.println(result);
        return result;
    }

    public void confirmTransfer(long transferId) {
        Optional<Transfer> transfer = transferRepository.findById(transferId);
        if (transfer.isPresent()) {
            Transfer retrievedTransfer = transfer.get();
            retrievedTransfer.setConfirmedByAdmin(true);
            transferRepository.save(retrievedTransfer);
        }
    }

    public List<Transfer> getUnconfirmedTransfers() {
        Iterable<Transfer> history = transferRepository.findAll();
        List<Transfer> result = new ArrayList<>();
        history.forEach(result::add);

        return result.stream().filter(not(Transfer::isConfirmedByAdmin)).collect(Collectors.toList());
    }
}
