package br.com.fiap.trabalho_final;

import br.com.fiap.trabalho_final.Controller.TransactionController;
import br.com.fiap.trabalho_final.Domain.new_transaction;
import br.com.fiap.trabalho_final.Repository.AllTransactionDao;
import br.com.fiap.trabalho_final.Repository.NewTransactionDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TrabalhoFinalApplicationTests {


	@Autowired
	private MockMvc mvc;

	@MockBean
	private AllTransactionDao repositoryAllTransaction;
	private NewTransactionDao repositoryNewTransaction;

	@Test
	public void SaveTransaction() throws Exception {

		repositoryNewTransaction = mock(NewTransactionDao.class);

		new_transaction newTransaction = new new_transaction();
		newTransaction.setAmount(System.currentTimeMillis());
		newTransaction.setAmount(25000.15);

		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		String jsonConvertedToString = mapper.writeValueAsString(newTransaction);

		when(this.repositoryNewTransaction.save(newTransaction)).thenReturn(HttpStatus.NO_CONTENT);

		mvc.perform(post("/transactions")
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonConvertedToString))
				.andExpect(status().isNoContent());
	}

}
