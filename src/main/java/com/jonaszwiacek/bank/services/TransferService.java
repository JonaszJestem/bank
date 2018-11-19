package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.Transfer;
import com.jonaszwiacek.bank.models.User;
import com.jonaszwiacek.bank.repositories.InMemoryTransfers;
import com.jonaszwiacek.bank.repositories.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    private TransferRepository transferRepository;
    private InMemoryTransfers inMemoryTransfers;

    public TransferService(TransferRepository transferRepository, InMemoryTransfers inMemoryTransfers) {
        this.transferRepository = transferRepository;
        this.inMemoryTransfers = inMemoryTransfers;
    }

    public void transfer(Transfer transfer) {
        String email = transfer.getEmail();
        Transfer confirmedTransfer = inMemoryTransfers.findByEmail(email);
        transferRepository.save(confirmedTransfer);
    }

    public void saveTemporary(Transfer transfer) {
        inMemoryTransfers.save(transfer);
    }

    public List<Transfer> getHistory(User user) {
        return transferRepository.findByEmail(user.getEmail());
    }
}
