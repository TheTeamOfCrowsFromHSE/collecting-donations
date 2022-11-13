package com._2dmes_.collectingdonations.components;

import com._2dmes_.collectingdonations.websocket.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import com._2dmes_.collectingdonations.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import com._2dmes_.collectingdonations.repositories.TransactionRepository;

import java.util.List;

import javafx.util.Pair;

@Component
public class MainComponent {
    private int threadInterruptionTime = 5000;

    @Autowired
    private DataBaseComponent db;

    @Autowired
    private TransactionRepository transactionRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        Runnable task = () -> {
            while (true) {
                //System.out.println("Check new transaction");
                try {
                    Pair<Boolean, List<Transaction>> transactions = DataBaseComponent.checkNewTransactions(transactionRepository);
                    if (transactions.getKey()) {
                        db.addNewTransactionsInDataBase(transactions.getValue());
                    }
                    Thread.sleep(threadInterruptionTime);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
