package com.portofolio.wallet.repository;

import com.portofolio.wallet.entity.Transaction;
import com.portofolio.wallet.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findByReferenceId(String referenceId);
    Page<Transaction> findByWallet(Wallet wallet, Pageable pageable);
    boolean existsByReferenceId(String referenceId);
}
