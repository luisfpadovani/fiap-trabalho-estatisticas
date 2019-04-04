package br.com.fiap.trabalho_final.Repository;

import br.com.fiap.trabalho_final.Domain.new_transaction;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class NewTransactionDao {
    private ArrayList<new_transaction> transactionArray = null;

    public NewTransactionDao(){
        transactionArray = new ArrayList<>();
    }

    public ArrayList<new_transaction> getList(){
        return transactionArray;
    }

    public HttpStatus save(new_transaction transaction){
        HttpStatus status = HttpStatus.CREATED;
        transactionArray.add(transaction);
        if(transaction.getTimestamp() >= 6000) {
            status = HttpStatus.NO_CONTENT;
        }
        return  status;
    }
}
