package com.example.Remittance.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Remittance.dao.FundTransferRepository;
import com.example.Remittance.dao.UserRepository;
import com.example.Remittance.entity.FundTransfer;
import com.example.Remittance.entity.User;
import com.example.Remittance.enums.Currency;
import com.example.Remittance.service.CurrencyConversionService;
import com.example.Remittance.service.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FundTransferRepository fundTransferRepository;
    
    @Autowired
    private CurrencyConversionService currencyConversionService;
    
    @Override
    @Transactional
    public FundTransfer createFundTransfer(String senderPhoneNumber, Long receiverId, Double amount, String fromCountry, Currency currency) {
        User sender = userRepository.findByPhoneNumber(senderPhoneNumber).orElseThrow(() -> new IllegalArgumentException("Sender not found with id: " + senderPhoneNumber));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with id: " + receiverId));
        
        double claimAmountInUsd = currencyConversionService.convertToUsd(amount, currency);
        FundTransfer fundTransfer = new FundTransfer();
        fundTransfer.setSender(sender);
        fundTransfer.setReceiver(receiver);
        fundTransfer.setTransactionAmount(claimAmountInUsd);
        fundTransfer.setFromCountry(fromCountry);
        fundTransfer.setCurrency(currency);;
        return fundTransferRepository.save(fundTransfer);
    }

    @Override
    public List<FundTransfer> getFundsReceivedByReceiverId(Long receiverId) {
        return fundTransferRepository.findByReceiver_UserId(receiverId);
    }
}
