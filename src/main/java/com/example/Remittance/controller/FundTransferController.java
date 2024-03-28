package com.example.Remittance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Remittance.dto.FundTransferRequest;
import com.example.Remittance.entity.FundTransfer;
import com.example.Remittance.service.FundTransferService;

@RestController
@RequestMapping("/api/fund-transfers")
public class FundTransferController {

    @Autowired
    private FundTransferService fundTransferService;

    @PostMapping
    public ResponseEntity<FundTransfer> createFundTransfer(@RequestBody FundTransferRequest request) {
        FundTransfer fundTransfer = fundTransferService.createFundTransfer(
                request.getSenderId(), 
                request.getReceiverId(), 
                request.getAmount(), 
                request.getFromCountry());

        return new ResponseEntity<>(fundTransfer, HttpStatus.CREATED);
    }
    
    @GetMapping("/received/{receiverId}")
    public ResponseEntity<List<FundTransfer>> getFundsReceivedByReceiverId(@PathVariable Long receiverId) {
        List<FundTransfer> fundTransfers = fundTransferService.getFundsReceivedByReceiverId(receiverId);
        return ResponseEntity.ok(fundTransfers);
    }
}
