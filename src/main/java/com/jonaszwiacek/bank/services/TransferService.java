package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.Transfer;
import com.jonaszwiacek.bank.repositories.InMemoryTransfers;
import com.jonaszwiacek.bank.repositories.TransferRepository;
import com.jonaszwiacek.bank.security.UserPrincipal;
import com.jonaszwiacek.bank.services.exceptions.NoTranfserFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    private TransferRepository transferRepository;
    private InMemoryTransfers inMemoryTransfers;

    public TransferService(TransferRepository transferRepository, InMemoryTransfers inMemoryTransfers) {
        this.transferRepository = transferRepository;
        this.inMemoryTransfers = inMemoryTransfers;

        Transfer transfer = new Transfer();
        transfer.setAmount("100");
        transfer.setEmail("email@email.com");
        transfer.setTitle("title");
        transfer.setUsername("login");
        transferRepository.save(transfer);
    }

    @Transactional
    public String transfer(UserPrincipal currentUser) {
        Transfer confirmedTransfer = inMemoryTransfers.findByEmailOrUsername(currentUser.getEmail(), currentUser.getUsername());
        System.out.println(currentUser);
        System.out.println(confirmedTransfer);
        if (confirmedTransfer != null) {
            Transfer transfer = new Transfer();
            transfer.setEmail(confirmedTransfer.getEmail());
            transfer.setUsername(confirmedTransfer.getUsername());
            transfer.setTitle(confirmedTransfer.getTitle());
            transfer.setAmount(confirmedTransfer.getAmount());
            inMemoryTransfers.deleteByUsernameOrEmail(confirmedTransfer.getUsername(), confirmedTransfer.getEmail());
            transferRepository.save(transfer);
            return "{\"message\": \"OK.\"}";
        } else {
            throw new NoTranfserFoundException();
        }
    }

    @Transactional
    public void saveTemporary(UserPrincipal currentUser, Transfer transfer) {
        String username = currentUser.getUsername();
        transfer.setUsername(username);
        String email = currentUser.getEmail();
        transfer.setEmail(email);
        System.out.println(currentUser);
        System.out.println(transfer);
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
}
