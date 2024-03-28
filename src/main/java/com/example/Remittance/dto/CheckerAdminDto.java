package com.example.Remittance.dto;

import java.time.LocalDate;

public class CheckerAdminDto {
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private LocalDate transactionDate;
    private Long transactionId;
    private String swiftNumberOfReceiver;
    private Double totalAmountInUsd;
    private Double claimedAmount;
    private String accountNumber;
    private Long daysPending;
	public CheckerAdminDto(Long senderId, String senderName, Long receiverId, String receiverName,
			LocalDate transactionDate, Long transactionId, String swiftNumberOfReceiver, Double totalAmountInUsd,
			Double claimedAmount, String accountNumber, Long daysPending) {
		super();
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.transactionDate = transactionDate;
		this.transactionId = transactionId;
		this.swiftNumberOfReceiver = swiftNumberOfReceiver;
		this.totalAmountInUsd = totalAmountInUsd;
		this.claimedAmount = claimedAmount;
		this.accountNumber = accountNumber;
		this.daysPending = daysPending;
	}
	public CheckerAdminDto() {
		super();
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getSwiftNumberOfReceiver() {
		return swiftNumberOfReceiver;
	}
	public void setSwiftNumberOfReceiver(String swiftNumberOfReceiver) {
		this.swiftNumberOfReceiver = swiftNumberOfReceiver;
	}
	public Double getTotalAmountInUsd() {
		return totalAmountInUsd;
	}
	public void setTotalAmountInUsd(Double totalAmountInUsd) {
		this.totalAmountInUsd = totalAmountInUsd;
	}
	public Double getClaimedAmount() {
		return claimedAmount;
	}
	public void setClaimedAmount(Double claimedAmount) {
		this.claimedAmount = claimedAmount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Long getDaysPending() {
		return daysPending;
	}
	public void setDaysPending(Long daysPending) {
		this.daysPending = daysPending;
	}
}
   
