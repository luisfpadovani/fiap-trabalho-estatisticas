package br.com.fiap.trabalho_final;

import br.com.fiap.trabalho_final.Controller.TransactionController;
import br.com.fiap.trabalho_final.Domain.all_transaction;
import br.com.fiap.trabalho_final.Domain.new_transaction;
import br.com.fiap.trabalho_final.Repository.AllTransactionDao;
import br.com.fiap.trabalho_final.Repository.NewTransactionDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	public void SaveTransaction_OK() throws Exception {

		repositoryNewTransaction = mock(NewTransactionDao.class);

		new_transaction newTransaction = new new_transaction();
		newTransaction.setAmount(System.currentTimeMillis());
		newTransaction.setAmount(25000.15);
		newTransaction.setDataInsert(System.currentTimeMillis());
		when(this.repositoryNewTransaction.save(newTransaction)).thenReturn(HttpStatus.OK);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		MockHttpServletRequestBuilder request = post("/transactions");
		request.content(new ObjectMapper().writeValueAsString(newTransaction));
		request.accept(MEDIA_TYPE_JSON_UTF8);
		request.contentType(MEDIA_TYPE_JSON_UTF8);
		mvc.perform(request).andExpect(status().isOk());
	}

	@Test
	public void SaveTransaction_NO_CONTENT() throws Exception {

		repositoryNewTransaction = mock(NewTransactionDao.class);

		new_transaction newTransaction = new new_transaction();
		newTransaction.setAmount(System.currentTimeMillis());
		newTransaction.setAmount(25000.15);
		newTransaction.setDataInsert(System.currentTimeMillis());
		when(this.repositoryNewTransaction.save(newTransaction)).thenReturn(HttpStatus.NO_CONTENT);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		MockHttpServletRequestBuilder request = post("/transactions");
		request.content(new ObjectMapper().writeValueAsString(newTransaction));
		request.accept(MEDIA_TYPE_JSON_UTF8);
		request.contentType(MEDIA_TYPE_JSON_UTF8);
		mvc.perform(request).andExpect(status().isNoContent());
	}

	@Test(expected=NullPointerException.class)
	public void GetNotFounded() throws Exception {
		ArrayList<new_transaction> allTransaction = new ArrayList<>();
		allTransaction = this.repositoryNewTransaction.getList();

		when(this.repositoryAllTransaction.resume(allTransaction)).thenReturn(null);
		mvc.perform(get("/statistics/")
				.accept(MediaType.APPLICATION_JSON));
	}
}
