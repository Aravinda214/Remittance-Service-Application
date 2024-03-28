package com.example.Remittance.dto;

import java.time.LocalDate;

public class WithdrawalRequestDto {
    private String senderName;
    private LocalDate initiatedDate;
    private String swiftNumber;
    private Double transactionAmount;
    private long daysPending;
    private String rejectionReason;

	public WithdrawalRequestDto(String senderName, LocalDate initiatedDate, String swiftNumber,
			Double transactionAmount, long daysPending, String rejectionReason) {
		super();
		this.senderName = senderName;
		this.initiatedDate = initiatedDate;
		this.swiftNumber = swiftNumber;
		this.transactionAmount = transactionAmount;
		this.daysPending = daysPending;
		this.rejectionReason = rejectionReason;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public LocalDate getInitiatedDate() {
		return initiatedDate;
	}

	public void setInitiatedDate(LocalDate initiatedDate) {
		this.initiatedDate = initiatedDate;
	}

	public String getSwiftNumber() {
		return swiftNumber;
	}

	public void setSwiftNumber(String swiftNumber) {
		this.swiftNumber = swiftNumber;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public long getDaysPending() {
		return daysPending;
	}

	public void setDaysPending(long daysPending) {
		this.daysPending = daysPending;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	@Override
	public String toString() {
		return "WithdrawalRequestDto [senderName=" + senderName + ", initiatedDate=" + initiatedDate + ", swiftNumber="
				+ swiftNumber + ", transactionAmount=" + transactionAmount + ", daysPending=" + daysPending + "]";
	}
    
    
}

