package com.portofolio.wallet.repository;

import com.portofolio.wallet.entity.User;
import com.portofolio.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUser(User userId);
}
