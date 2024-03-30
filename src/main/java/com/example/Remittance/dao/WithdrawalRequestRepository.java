package com.example.Remittance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Remittance.entity.WithdrawalRequest;
import com.example.Remittance.enums.WithdrawalStatus;

import java.util.List;

public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, Long> {
	@Query("SELECT wr FROM WithdrawalRequest wr WHERE wr.receiver.userId = :receiverId AND wr.status = :status")
	List<WithdrawalRequest> findByReceiverIdAndStatus(@Param("receiverId") Long receiverId, @Param("status") WithdrawalStatus status);
	
	List<WithdrawalRequest> findByStatus(WithdrawalStatus status);

	@Query("SELECT SUM(wr.claimAmount) FROM WithdrawalRequest wr WHERE wr.fundTransfer.id = :fundTransferId AND wr.id != :excludeRequestId")
	Double sumClaimAmountByFundTransferIdAndExcludeCurrentRequest(@Param("fundTransferId") Long fundTransferId, @Param("excludeRequestId") Long excludeRequestId);

	List<WithdrawalRequest> findByStatusAndCheckerId(WithdrawalStatus settled, Long checkerId);
}

