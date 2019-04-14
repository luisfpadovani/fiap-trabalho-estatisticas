package br.com.fiap.trabalho_final.Controller;
import br.com.fiap.trabalho_final.Domain.all_transaction;
import br.com.fiap.trabalho_final.Domain.new_transaction;
import br.com.fiap.trabalho_final.Repository.AllTransactionDao;
import br.com.fiap.trabalho_final.Repository.NewTransactionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.time.LocalDateTime;
import io.swagger.annotations.Api;


@RestController
@Api(value = "Transaction", description = "API with services transactions")
public class TransactionController {

    public NewTransactionDao daoNewTransactionDao;
    public AllTransactionDao daoAllTransactionDao;

    public TransactionController() {
        daoNewTransactionDao = new NewTransactionDao();
        daoAllTransactionDao = new AllTransactionDao();
    }

    @PostMapping("/transactions")
    public ResponseEntity save(@RequestBody new_transaction transaction){
        return new ResponseEntity(daoNewTransactionDao.save(transaction));
    }
    @GetMapping("/statistics")
    public ResponseEntity<all_transaction> get(){
        return new ResponseEntity(daoAllTransactionDao.resume(daoNewTransactionDao.getList()), HttpStatus.OK);
    }


}

