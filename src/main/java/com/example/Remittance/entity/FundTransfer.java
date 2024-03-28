package com.example.Remittance.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.example.Remittance.enums.TransferStatus;

@Entity
public class FundTransfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transactionDate = new Date(); // System date when creating

    @Column(nullable = false)
    private Double transactionAmount; // Always in USD

    @Column(nullable = false, unique = true)
    private String swiftNumber; // Auto-generated

    @Column(nullable = false)
    private String fromCountry;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferStatus status = TransferStatus.PENDING;

    @PrePersist
    private void generateSwiftNumber() {
        // Assuming a simple format for SWIFT numbers: "FT" + UUID
        // This is for demonstration purposes. SWIFT numbers have a specific format in real banking systems.
        this.swiftNumber = "FT" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getSwiftNumber() {
        return swiftNumber;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }
}

