package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.domains.edu.model.EWallet;
import com.talentboozt.edu_service.domains.edu.model.EWalletTransaction;
import com.talentboozt.edu_service.domains.edu.service.EduWalletService;
import com.talentboozt.edu_service.domains.edu.repository.mongodb.EWalletTransactionRepository;
import com.talentboozt.edu_service.shared.security.annotations.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/edu/wallet")
@RequiredArgsConstructor
public class EduWalletController {

    private final EduWalletService walletService;
    private final EWalletTransactionRepository transactionRepository;

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ENTERPRISE_INSTRUCTOR') or hasAuthority('SELLER_FREE') or hasAuthority('SELLER_PRO') or hasAuthority('SELLER_PREMIUM')")
    public ResponseEntity<EWallet> getMyWallet(@AuthenticatedUser String userId) {
        return ResponseEntity.ok(walletService.getOrCreateWallet(userId));
    }

    @GetMapping("/me/transactions")
    @PreAuthorize("hasAuthority('ENTERPRISE_INSTRUCTOR') or hasAuthority('SELLER_FREE') or hasAuthority('SELLER_PRO') or hasAuthority('SELLER_PREMIUM')")
    public ResponseEntity<List<EWalletTransaction>> getMyTransactions(@AuthenticatedUser String userId) {
        return ResponseEntity.ok(transactionRepository.findByUserId(userId));
    }
}
