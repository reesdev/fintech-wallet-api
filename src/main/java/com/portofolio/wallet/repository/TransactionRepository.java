package com.portofolio.wallet.repository;

import com.portofolio.wallet.entity.Transaction;
import com.portofolio.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findByReferenceId(String referenceId);
    List<Transaction> findByWallet(Wallet wallet);
}
