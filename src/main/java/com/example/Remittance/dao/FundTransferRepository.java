package com.example.Remittance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Remittance.entity.FundTransfer;

public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {
	List<FundTransfer> findByReceiver_UserId(Long receiverId);
}
