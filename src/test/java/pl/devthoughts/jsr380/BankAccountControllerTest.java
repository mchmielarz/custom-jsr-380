package pl.devthoughts.jsr380;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.devthoughts.jsr380.domain.BankAccountFacade;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
@ContextConfiguration(classes = {BankAccountControllerTest.Config.class})
public class BankAccountControllerTest {
    
    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void should_be_ok() throws Exception {
        final String jsonRequest = asJson(
          request("PL10105000997603123456789123", "EBOSPLPW", "7603123456789123"));
        mvc
          .perform(put("/bank-accounts/" + randomUUID())
                      .content(jsonRequest)
                      .contentType(APPLICATION_JSON)
          )
          .andExpect(status().isOk());
    }

    @Test
    public void should_fail_miserably() throws Exception {
        final String request = asJson(
          request("iban", "bic", null));
        mvc
          .perform(put("/bank-accounts/" + randomUUID())
                     .content(request)
                     .contentType(APPLICATION_JSON)
          )
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.fieldErrors").value(hasSize(3)));
    }

    private String asJson(CreateBankAccountRequest req) throws Exception {
        return mapper.writeValueAsString(req);
    }

    private CreateBankAccountRequest request(String iban, String bicCode, String bankAccountNumber) {
        final CreateBankAccountRequest request = new CreateBankAccountRequest();
        request.setIban(iban);
        request.setBic(bicCode);
        request.setBankAccountNumber(bankAccountNumber);
        return request;
    }

    @Configuration
    static class Config {

        @Bean
        BankAccountFacade bankAccountFacade() {
            return mock(BankAccountFacade.class);
        }

        @Bean
        BankAccountController bankAccountController(BankAccountFacade bankAccountFacade) {
            return new BankAccountController(bankAccountFacade);
        }

    }
}
