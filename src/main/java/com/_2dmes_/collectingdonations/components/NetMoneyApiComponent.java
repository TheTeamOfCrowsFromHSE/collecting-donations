package com._2dmes_.collectingdonations.components;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com._2dmes_.collectingdonations.models.Transaction;

import java.util.ArrayList;
import java.util.List;

@Component
public class NetMoneyApiComponent {
    static public List<Transaction> getNewTransactions(String lastTransactionId) throws org.json.simple.parser.ParseException { // return json
        HHTPComponent.createHeaders();
        HHTPComponent.createRequest();
        ResponseEntity<String> response = HHTPComponent.getResponse();
        System.out.println(response);

        String dataJSON = response.getBody();

        JSONArray allTransactionJSON = ParserJSON.getAllTransactionJSON(dataJSON);

        List<Transaction> transactions = new ArrayList<>();
        for (Object transactionObject : allTransactionJSON) {
            JSONObject transactionJSON = (JSONObject) transactionObject;
            if (lastTransactionId.equals(transactionJSON.get("id"))) {
                return transactions;
            }
            String idTransaction = (String) transactionJSON.get("id");
            String name = (String) transactionJSON.get("name");
            String lastName = (String) transactionJSON.get("lastName");
            String code = (String) transactionJSON.get("code");
            String message = (String) transactionJSON.get("message");
            String amount = (String) transactionJSON.get("amount");

            Transaction transaction = new Transaction(idTransaction, name, lastName, code, message, amount);
            transactions.add(0, transaction);
        }
        return transactions;
    }
}
