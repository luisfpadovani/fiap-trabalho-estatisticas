package br.com.fiap.trabalho_final.Repository;

import br.com.fiap.trabalho_final.Domain.all_transaction;
import br.com.fiap.trabalho_final.Domain.new_transaction;

import java.util.ArrayList;

public class AllTransactionDao {

    public all_transaction resume(ArrayList<new_transaction> transactionArray){
        all_transaction allTransaction = new all_transaction();
        allTransaction.setSum(transactionArray.stream().mapToDouble(a -> a.getAmount()).sum());
        allTransaction.setAvg(transactionArray.stream().mapToDouble(a -> a.getAmount()).average().getAsDouble());
        allTransaction.setMin(transactionArray.stream().mapToDouble(a -> a.getAmount()).min().getAsDouble());
        allTransaction.setMax(transactionArray.stream().mapToDouble(a -> a.getAmount()).max().getAsDouble());
        allTransaction.setCount(transactionArray.size());
        return allTransaction;
    }

}
