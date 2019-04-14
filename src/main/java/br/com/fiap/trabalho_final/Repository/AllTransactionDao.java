package br.com.fiap.trabalho_final.Repository;

import br.com.fiap.trabalho_final.Domain.all_transaction;
import br.com.fiap.trabalho_final.Domain.new_transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllTransactionDao {

    public all_transaction resume(ArrayList<new_transaction> transactionArray){
        long dataHoraAtual = System.currentTimeMillis();
        int segundos = 60000;
        all_transaction allTransaction = new all_transaction();
        List<new_transaction> result = transactionArray.stream().filter(a -> (dataHoraAtual - a.getDataInsert())  <= segundos).collect(Collectors.toList());
        allTransaction.setSum(result.stream().mapToDouble(a -> a.getAmount()).sum());
        allTransaction.setAvg(result.stream().mapToDouble(a -> a.getAmount()).average().getAsDouble());
        allTransaction.setMin(result.stream().mapToDouble(a -> a.getAmount()).min().getAsDouble());
        allTransaction.setMax(result.stream().mapToDouble(a -> a.getAmount()).max().getAsDouble());
        allTransaction.setCount(result.size());
        return allTransaction;
    }
}
