package com.example.Remittance.controller;

import com.example.Remittance.dto.CheckerAdminDto;
import com.example.Remittance.dto.WithdrawalRequestDto;
import com.example.Remittance.entity.WithdrawalRequest;
import com.example.Remittance.service.WithdrawalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/withdrawals")
public class WithdrawalRequestController {

    @Autowired
    private WithdrawalRequestService withdrawalRequestService;

    @PostMapping
    public ResponseEntity<WithdrawalRequest> createWithdrawalRequest(@RequestParam Long fundTransferId, 
                                                                     @RequestBody WithdrawalRequest withdrawalRequest) {
        WithdrawalRequest createdRequest = withdrawalRequestService.createWithdrawalRequest(fundTransferId, withdrawalRequest);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("/pending/{receiverId}")
    public ResponseEntity<List<WithdrawalRequestDto>> findPendingWithdrawalsByReceiverId(@PathVariable Long receiverId) {
        return ResponseEntity.ok(withdrawalRequestService.findPendingWithdrawalsByReceiverId(receiverId));
    }

    @GetMapping("/expired/{receiverId}")
    public ResponseEntity<List<WithdrawalRequestDto>> findExpiredWithdrawalsByReceiverId(@PathVariable Long receiverId) {
        return ResponseEntity.ok(withdrawalRequestService.findExpiredWithdrawalsByReceiverId(receiverId));
    }

    @GetMapping("/settled/{receiverId}")
    public ResponseEntity<List<WithdrawalRequestDto>> findSettledWithdrawalsByReceiverId(@PathVariable Long receiverId) {
        return ResponseEntity.ok(withdrawalRequestService.findSettledWithdrawalsByReceiverId(receiverId));
    }

    @GetMapping("/rejected/{receiverId}")
    public ResponseEntity<List<WithdrawalRequestDto>> findRejectedWithdrawalsByReceiverId(@PathVariable Long receiverId) {
        return ResponseEntity.ok(withdrawalRequestService.findRejectedWithdrawalsByReceiverId(receiverId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<CheckerAdminDto>> findAllPendingWithdrawals() {
        return ResponseEntity.ok(withdrawalRequestService.findAllPendingWithdrawals());
    }
    
    @PutMapping("/{withdrawalRequestId}")
    public ResponseEntity<WithdrawalRequest> updateWithdrawalRequest(@PathVariable Long withdrawalRequestId, 
                                                                      @RequestParam Double newClaimAmountInUsd) {
        WithdrawalRequest updatedRequest = withdrawalRequestService.updateWithdrawalRequest(withdrawalRequestId, newClaimAmountInUsd);
        return ResponseEntity.ok(updatedRequest);
    }
    
    @PutMapping("/{withdrawalRequestId}/approve")
    public ResponseEntity<?> approveWithdrawalRequest(@PathVariable Long withdrawalRequestId,
                                                      @RequestParam Long checkerId) {
        try {
        	withdrawalRequestService.approveWithdrawalRequest(withdrawalRequestId, checkerId);
            return ResponseEntity.ok().body("Withdrawal request approved successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{withdrawalRequestId}/reject")
    public ResponseEntity<?> rejectWithdrawalRequest(@PathVariable Long withdrawalRequestId,
                                                     @RequestParam String rejectionReason,
                                                     @RequestParam Long checkerId) {
        try {
        	withdrawalRequestService.rejectWithdrawalRequest(withdrawalRequestId, rejectionReason, checkerId);
            return ResponseEntity.ok().body("Withdrawal request rejected!");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/settled")
    public ResponseEntity<List<WithdrawalRequest>> getSettledWithdrawalRequestsByCheckerId(@RequestParam Long checkerId) {
        List<WithdrawalRequest> settledRequests = withdrawalRequestService.getSettledWithdrawalRequestsByCheckerId(checkerId);
        return ResponseEntity.ok(settledRequests);
    }

    @GetMapping("/rejected")
    public ResponseEntity<List<WithdrawalRequest>> getRejectedWithdrawalRequestsByCheckerId(@RequestParam Long checkerId) {
        List<WithdrawalRequest> rejectedRequests = withdrawalRequestService.getRejectedWithdrawalRequestsByCheckerId(checkerId);
        return ResponseEntity.ok(rejectedRequests);
    }
}
