package com.jonaszwiacek.bank.controllers;

import com.jonaszwiacek.bank.models.Transfer;
import com.jonaszwiacek.bank.services.TransferService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private TransferService transferService;

    public AdminController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/confirm")
    @Secured({"ROLE_ADMIN"})
    public void confirmTransfer(@RequestBody Long transferId) {
        transferService.confirmTransfer(transferId);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public List<Transfer> getUnconfirmedTransfers() {
        return transferService.getUnconfirmedTransfers();
    }
}
