package pl.devthoughts.jsr380.domain;

import org.junit.Test;

import pl.devthoughts.jsr380.domain.dto.BankAccountData;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {

    @Test
    public void should_be_ok() {
        BankAccount bankAccount = newBankAccount("PL10105000997603123456789123", "EBOSPLPW",
                                                 "7603123456789123");

        final Set<ConstraintViolation<BankAccount>> constraints = getValidator().validate(bankAccount);

        assertThat(constraints).isEmpty();
    }

    @Test
    public void should_fail_miserably() {
        BankAccount bankAccount = newBankAccount("invalid_iban", "invalid_bic", null);

        final Set<ConstraintViolation<BankAccount>> constraints = getValidator().validate(bankAccount);

        constraints.forEach(System.out::println);

        assertThat(constraints).hasSize(3);
    }

    private BankAccount newBankAccount(String iban, String bicCode, String bankAccountNumber) {
        BankAccountData data = new BankAccountData(iban, bicCode, bankAccountNumber);
        return new BankAccount(data);
    }

    private Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
