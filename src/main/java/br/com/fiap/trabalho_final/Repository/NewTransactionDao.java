package br.com.fiap.trabalho_final.Repository;

import br.com.fiap.trabalho_final.Domain.new_transaction;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NewTransactionDao {
    public ArrayList<new_transaction> transactionArray = null;

    public NewTransactionDao(){
        transactionArray = new ArrayList<>();
    }

    public ArrayList<new_transaction> getList(){
        return transactionArray;
    }

    public HttpStatus save(new_transaction transaction){

        long millisSecondsAtual = System.currentTimeMillis();
        long timeSpanSave = transaction.getTimestamp();

        long sobra = TimeUnit.MILLISECONDS.toSeconds(millisSecondsAtual - timeSpanSave);

        transaction.setDataInsert(timeSpanSave);


        HttpStatus status = HttpStatus.CREATED;
        transactionArray.add(transaction);
        if(sobra >= 60) {
            status = HttpStatus.NO_CONTENT;
        }
        return  status;
    }

}
