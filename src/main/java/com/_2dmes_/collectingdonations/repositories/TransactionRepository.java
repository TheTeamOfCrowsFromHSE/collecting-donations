package com._2dmes_.collectingdonations.repositories;

import com._2dmes_.collectingdonations.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Transaction findTopByOrderByIdDesc();
}

