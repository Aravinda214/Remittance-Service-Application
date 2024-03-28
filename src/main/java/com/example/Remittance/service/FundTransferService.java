package com.example.Remittance.service;

import java.util.List;

import com.example.Remittance.entity.FundTransfer;

public interface FundTransferService {
    FundTransfer createFundTransfer(Long senderId, Long receiverId, Double amount, String fromCountry);
    List<FundTransfer> getFundsReceivedByReceiverId(Long receiverId);
}
