package com.example.Remittance.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.example.Remittance.enums.Currency;
import com.example.Remittance.enums.WithdrawalStatus;

@Entity
public class WithdrawalRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private User sender;

    @ManyToOne // Establishing the relationship with FundTransfer
    private FundTransfer fundTransfer;

    @Column(nullable = false, unique = true)
    private String swiftNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date requestDate = new Date();


    @Column(nullable = false)
    private Double claimAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = true)
    private Double conversionRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WithdrawalStatus status = WithdrawalStatus.PENDING;

    @Column(nullable = true)
    private String rejectionReason;
    
    @Column(nullable = true)
    private Long checkerId;

    @PrePersist
    private void generateSwiftNumber() {
        // Example of auto-generating a SWIFT number. Adjust the generation logic as needed.
        this.swiftNumber = "SWIFT" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }

	public WithdrawalRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WithdrawalRequest(Long id, User receiver, User sender, FundTransfer fundTransfer, String swiftNumber,
			Date currentDate, Double claimAmount, Currency currency, Double conversionRate, WithdrawalStatus status,
			String rejectionReason) {
		super();
		this.id = id;
		this.receiver = receiver;
		this.sender = sender;
		this.fundTransfer = fundTransfer;
		this.swiftNumber = swiftNumber;
		this.requestDate = currentDate;
		this.claimAmount = claimAmount;
		this.currency = currency;
		this.conversionRate = conversionRate;
		this.status = status;
		this.rejectionReason = rejectionReason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public FundTransfer getFundTransfer() {
		return fundTransfer;
	}

	public void setFundTransfer(FundTransfer fundTransfer) {
		this.fundTransfer = fundTransfer;
	}

	public String getSwiftNumber() {
		return swiftNumber;
	}

	public void setSwiftNumber(String swiftNumber) {
		this.swiftNumber = swiftNumber;
	}

	public Date getCurrentDate() {
		return requestDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.requestDate = currentDate;
	}

	public Double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public WithdrawalStatus getStatus() {
		return status;
	}

	public void setStatus(WithdrawalStatus status) {
		this.status = status;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Long getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(Long checkerId) {
		this.checkerId = checkerId;
	}

	@Override
	public String toString() {
		return "WithdrawalRequest [id=" + id + ", receiver=" + receiver + ", sender=" + sender + ", fundTransfer="
				+ fundTransfer + ", swiftNumber=" + swiftNumber + ", currentDate=" + requestDate + ", claimAmount="
				+ claimAmount + ", currency=" + currency + ", conversionRate=" + conversionRate + ", status=" + status
				+ ", rejectionReason=" + rejectionReason + "]";
	}
    
    
}
