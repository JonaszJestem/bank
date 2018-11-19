package com.jonaszwiacek.bank.controllers;

import com.jonaszwiacek.bank.models.Transfer;
import com.jonaszwiacek.bank.models.User;
import com.jonaszwiacek.bank.services.TransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/perform")
    public void performTransfer(@RequestBody Transfer transfer) {
        transferService.transfer(transfer);
    }

    @PostMapping("/saveForConfirmation")
    public void saveTransfer(@RequestBody Transfer transfer) {
        transferService.saveTemporary(transfer);
    }

    @GetMapping
    public List<Transfer> getTransferHistory(@RequestBody User user) {
        return transferService.getHistory(user);
    }
}
