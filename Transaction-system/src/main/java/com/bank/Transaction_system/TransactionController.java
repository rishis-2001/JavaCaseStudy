package com.bank.Transaction_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    // POST endpoint to create a new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }


    // GET endpoint to retrieve a transaction by its ID
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    // GET endpoint to retrieve a list of all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // PUT endpoint to update an existing transaction
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    // Update fields with the new values from updatedTransaction
                    transaction.setTransactionType(updatedTransaction.getTransactionType());
                    transaction.setAmount(updatedTransaction.getAmount());
                    // Save the updated transaction
                    return transactionRepository.save(transaction);
                })
                .orElse(null); // If not found, return null
    }

    // DELETE endpoint to delete a transaction by its ID
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}