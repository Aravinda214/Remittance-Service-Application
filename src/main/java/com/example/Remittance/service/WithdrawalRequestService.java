package com.example.Remittance.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Remittance.dto.CheckerAdminDto;
import com.example.Remittance.dto.WithdrawalRequestDto;
import com.example.Remittance.entity.WithdrawalRequest;

public interface WithdrawalRequestService {
	WithdrawalRequest createWithdrawalRequest(Long fundTransferId, WithdrawalRequest withdrawalRequest);
	List<WithdrawalRequestDto> findPendingWithdrawalsByReceiverId(Long receiverId);
	WithdrawalRequest updateWithdrawalRequest(Long withdrawalRequestId, Double newClaimAmountInUsd);
	List<WithdrawalRequestDto> findExpiredWithdrawalsByReceiverId(Long s);
	List<WithdrawalRequestDto> findSettledWithdrawalsByReceiverId(Long receiverId);
	List<WithdrawalRequestDto> findRejectedWithdrawalsByReceiverId(Long receiverId);
	List<CheckerAdminDto> findAllPendingWithdrawals();
	void approveWithdrawalRequest(Long withdrawalRequestId, Long checkerId);
	void rejectWithdrawalRequest(Long withdrawalRequestId, String rejectionReason, Long checkerId);
	List<WithdrawalRequest> getSettledWithdrawalRequestsByCheckerId(Long checkerId);
	List<WithdrawalRequest> getRejectedWithdrawalRequestsByCheckerId(Long checkerId);
}
