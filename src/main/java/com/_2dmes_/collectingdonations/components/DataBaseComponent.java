package com._2dmes_.collectingdonations.components;

import org.springframework.stereotype.Component;
import org.apache.tomcat.util.json.ParseException;
import com._2dmes_.collectingdonations.models.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import com._2dmes_.collectingdonations.repositories.TransactionRepository;

import java.util.List;

import javafx.util.Pair;

@Component
public class DataBaseComponent {
    @Autowired
    private TransactionRepository transactionRepository;

    public static Pair<Boolean, List<Transaction>> checkNewTransactions(TransactionRepository transactionRepository) throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException {
        String lastTransactionId = getLastTransactionId(transactionRepository);
        List<Transaction> transactions = NetMoneyApiComponent.getNewTransactions(lastTransactionId);
        return new Pair<>(!transactions.isEmpty(), transactions);
    }

    public void addNewTransactionsInDataBase(List<Transaction> transactions) {
        if (!transactions.isEmpty()) {
            for (Transaction transaction : transactions) {
                addTransaction(transaction);
            }
        }
    }

    private void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    private static String getLastTransactionId(TransactionRepository transactionRepository) {
        if (transactionRepository.count() > 0) {
            Transaction transaction = transactionRepository.findTopByOrderByIdDesc();
            return transaction.getIdTransaction();
        }
        return "-1";
    }
}
