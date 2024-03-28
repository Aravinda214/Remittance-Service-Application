package com.example.Remittance.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Remittance.dao.FundTransferRepository;
import com.example.Remittance.dao.UserRepository;
import com.example.Remittance.entity.FundTransfer;
import com.example.Remittance.entity.User;
import com.example.Remittance.service.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FundTransferRepository fundTransferRepository;

    @Override
    @Transactional
    public FundTransfer createFundTransfer(Long senderId, Long receiverId, Double amount, String fromCountry) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found with id: " + senderId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with id: " + receiverId));

        FundTransfer fundTransfer = new FundTransfer();
        fundTransfer.setSender(sender);
        fundTransfer.setReceiver(receiver);
        fundTransfer.setTransactionAmount(amount);
        fundTransfer.setFromCountry(fromCountry);
        
        return fundTransferRepository.save(fundTransfer);
    }

    @Override
    public List<FundTransfer> getFundsReceivedByReceiverId(Long receiverId) {
        return fundTransferRepository.findByReceiver_UserId(receiverId);
    }
}
