package com.example.Remittance.serviceimpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Remittance.dao.FundTransferRepository;
import com.example.Remittance.dao.WithdrawalRequestRepository;
import com.example.Remittance.entity.FundTransfer;
import com.example.Remittance.entity.WithdrawalRequest;
import com.example.Remittance.enums.WithdrawalStatus;
import com.example.Remittance.service.CurrencyConversionService;
import com.example.Remittance.service.WithdrawalRequestService;
import com.example.Remittance.dto.CheckerAdminDto;
import com.example.Remittance.dto.WithdrawalRequestDto;

@Service
public class WithdrawalRequestServiceImpl implements WithdrawalRequestService  {

    @Autowired
    private WithdrawalRequestRepository withdrawalRequestRepository;

    @Autowired
    private FundTransferRepository fundTransferRepository;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Transactional
    @Override
    public WithdrawalRequest createWithdrawalRequest(Long fundTransferId, WithdrawalRequest withdrawalRequest) {
        FundTransfer fundTransfer = fundTransferRepository.findById(fundTransferId)
                .orElseThrow(() -> new IllegalArgumentException("FundTransfer not found"));

        double claimAmountInUsd = currencyConversionService.convertToUsd(withdrawalRequest.getClaimAmount(), withdrawalRequest.getCurrency());

        if (claimAmountInUsd > fundTransfer.getTransactionAmount()) {
            throw new IllegalArgumentException("Claim amount exceeds fund amount");
        }

        withdrawalRequest.setSender(fundTransfer.getSender());
        withdrawalRequest.setReceiver(fundTransfer.getReceiver());
        withdrawalRequest.setFundTransfer(fundTransfer); 
        withdrawalRequest.setClaimAmount(claimAmountInUsd);
        return withdrawalRequestRepository.save(withdrawalRequest);
    }
    
    @Transactional
    @Override
    public List<WithdrawalRequestDto> findPendingWithdrawalsByReceiverId(Long receiverId) {
        List<WithdrawalRequest> pendingRequests = withdrawalRequestRepository.findByReceiverIdAndStatus(receiverId, WithdrawalStatus.PENDING);
        return pendingRequests.stream().map(request -> {
            LocalDate initiatedDate = convertToLocalDateViaInstant(request.getCurrentDate());
            long daysPending = initiatedDate.until(LocalDate.now()).getDays();


            return new WithdrawalRequestDto(
                request.getSender().getUserName(),
                initiatedDate,
                request.getSwiftNumber(),
                request.getClaimAmount(),
                daysPending,
                request.getRejectionReason()
            );
        }).collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public WithdrawalRequest updateWithdrawalRequest(Long withdrawalRequestId, Double newClaimAmountInUsd) {
        WithdrawalRequest withdrawalRequest = withdrawalRequestRepository.findById(withdrawalRequestId)
                .orElseThrow(() -> new IllegalArgumentException("WithdrawalRequest not found"));

        Double totalClaimedExcludingCurrent = withdrawalRequestRepository.sumClaimAmountByFundTransferIdAndExcludeCurrentRequest(
                withdrawalRequest.getFundTransfer().getTransactionId(), withdrawalRequestId);

        if (totalClaimedExcludingCurrent == null) {
            totalClaimedExcludingCurrent = 0.0;
        }

        double updatedTotalClaimed = totalClaimedExcludingCurrent + newClaimAmountInUsd;

        if (updatedTotalClaimed > withdrawalRequest.getFundTransfer().getTransactionAmount()) {
            throw new IllegalArgumentException("Total claim amount exceeds available fund amount");
        }

        // Update the claim amount for the current withdrawal request
        withdrawalRequest.setClaimAmount(newClaimAmountInUsd);

        return withdrawalRequestRepository.save(withdrawalRequest);
    }
    
    @Transactional
    @Override
    public List<WithdrawalRequestDto> findExpiredWithdrawalsByReceiverId(Long receiverId) {
        List<WithdrawalRequest> pendingRequests = withdrawalRequestRepository.findByReceiverIdAndStatus(receiverId, WithdrawalStatus.EXPIRED);
        return pendingRequests.stream().map(request -> {
            LocalDate initiatedDate = convertToLocalDateViaInstant(request.getCurrentDate());
            long daysPending = initiatedDate.until(LocalDate.now()).getDays();


            return new WithdrawalRequestDto(
                request.getSender().getUserName(),
                initiatedDate,
                request.getSwiftNumber(),
                request.getClaimAmount(),
                daysPending,
                request.getRejectionReason()
            );
        }).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public List<WithdrawalRequestDto> findSettledWithdrawalsByReceiverId(Long receiverId) {
        List<WithdrawalRequest> pendingRequests = withdrawalRequestRepository.findByReceiverIdAndStatus(receiverId, WithdrawalStatus.SETTLED);
        return pendingRequests.stream().map(request -> {
            LocalDate initiatedDate = convertToLocalDateViaInstant(request.getCurrentDate());
            long daysPending = initiatedDate.until(LocalDate.now()).getDays();


            return new WithdrawalRequestDto(
                request.getSender().getUserName(),
                initiatedDate,
                request.getSwiftNumber(),
                request.getClaimAmount(),
                daysPending,
                request.getRejectionReason()
            );
        }).collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public List<WithdrawalRequestDto> findRejectedWithdrawalsByReceiverId(Long receiverId) {
        List<WithdrawalRequest> pendingRequests = withdrawalRequestRepository.findByReceiverIdAndStatus(receiverId, WithdrawalStatus.REJECTED);
        return pendingRequests.stream().map(request -> {
            LocalDate initiatedDate = convertToLocalDateViaInstant(request.getCurrentDate());
            long daysPending = initiatedDate.until(LocalDate.now()).getDays();


            return new WithdrawalRequestDto(
                request.getSender().getUserName(),
                initiatedDate,
                request.getSwiftNumber(),
                request.getClaimAmount(),
                daysPending,
                request.getRejectionReason()
            );
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<CheckerAdminDto> findAllPendingWithdrawals() {
        List<WithdrawalRequest> pendingRequests = withdrawalRequestRepository.findByStatus(WithdrawalStatus.PENDING);

        return pendingRequests.stream().map(request -> {
            LocalDate initiatedDate = convertToLocalDateViaInstant(request.getCurrentDate());
            long daysPending = LocalDate.now().until(initiatedDate).getDays();
            double claimedAmount = request.getClaimAmount(); // Assuming the claimed amount is already in the desired currency format

            return new CheckerAdminDto(
                    request.getSender().getUserId(),
                    request.getSender().getUserName(),
                    request.getReceiver().getUserId(),
                    request.getReceiver().getUserName(),
                    initiatedDate,
                    request.getId(),
                    request.getSwiftNumber(),
                    request.getFundTransfer().getTransactionAmount(), // Total amount in USD
                    claimedAmount, // No currency conversion applied
                    request.getReceiver().getBankAccountNumber(), // Assuming this is the receiver's account number
                    daysPending // Include days pending in the DTO
            );
        })
        .sorted(Comparator.comparingLong(CheckerAdminDto::getDaysPending).reversed()) // Sort by daysPending in descending order
        .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void approveWithdrawalRequest(Long withdrawalRequestId) {
        WithdrawalRequest withdrawalRequest = withdrawalRequestRepository.findById(withdrawalRequestId)
                .orElseThrow(() -> new IllegalStateException("Withdrawal request not found"));

        if (withdrawalRequest.getStatus() != WithdrawalStatus.PENDING) {
            throw new IllegalStateException("Withdrawal request is not in a pending state");
        }

        // Assuming you have a method or means to get the latest balance or handle the logic to deduct the amount
        FundTransfer fundTransfer = withdrawalRequest.getFundTransfer();
        double newAmount = fundTransfer.getTransactionAmount() - withdrawalRequest.getClaimAmount();
        if (newAmount < 0) {
            throw new IllegalStateException("Insufficient funds in the fund transfer to approve this withdrawal");
        }

        fundTransfer.setTransactionAmount(newAmount);
        fundTransferRepository.save(fundTransfer);

        withdrawalRequest.setStatus(WithdrawalStatus.SETTLED);
        withdrawalRequestRepository.save(withdrawalRequest);
    }
    
    @Override
    @Transactional
    public void rejectWithdrawalRequest(Long withdrawalRequestId, String rejectionReason) {
        WithdrawalRequest withdrawalRequest = withdrawalRequestRepository.findById(withdrawalRequestId)
                .orElseThrow(() -> new IllegalStateException("Withdrawal request not found"));

        if (withdrawalRequest.getStatus() != WithdrawalStatus.PENDING) {
            throw new IllegalStateException("Withdrawal request is not in a pending state");
        }

        withdrawalRequest.setStatus(WithdrawalStatus.REJECTED);
        withdrawalRequest.setRejectionReason(rejectionReason); // Ensure your WithdrawalRequest entity has a field for rejectionReason
        withdrawalRequestRepository.save(withdrawalRequest);
    }
    
    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

}
