package com.jonaszwiacek.bank.controllers;

import com.jonaszwiacek.bank.models.Transfer;
import com.jonaszwiacek.bank.security.CurrentUser;
import com.jonaszwiacek.bank.security.UserPrincipal;
import com.jonaszwiacek.bank.services.TransferService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = "http://localhost:3000")
public class TransferController {
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/perform")
    @Secured({"ROLE_USER"})
    public String performTransfer(@CurrentUser UserPrincipal currentUserr) {
        return transferService.transfer(currentUserr);
    }

    @PostMapping("/saveForConfirmation")
    @Secured({"ROLE_USER"})
    public Transfer saveTransfer(@CurrentUser UserPrincipal currentUser, @RequestBody Transfer transfer) {
        transferService.saveTemporary(currentUser, transfer);
        return transfer;
    }

    @GetMapping
    @Secured({"ROLE_USER"})
    public List<Transfer> getTransferHistory(@CurrentUser UserPrincipal currentUser) {
        return transferService.getHistory(currentUser);
    }
}
