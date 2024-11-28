package com.bank.Transaction_system;

import org.springframework.data.jpa.repository.JpaRepository;

public interface    TransactionRepository extends JpaRepository<Transaction, Long> {
}