package com.example.Remittance.service;

import java.util.List;

import com.example.Remittance.entity.FundTransfer;
import com.example.Remittance.enums.Currency;

public interface FundTransferService {
    FundTransfer createFundTransfer(String senderPhoneNumber, Long receiverId, Double amount, String fromCountry, Currency currency);
    List<FundTransfer> getFundsReceivedByReceiverId(Long receiverId);
}
