package pl.devthoughts.jsr380;

import lombok.Getter;
import lombok.Setter;

import pl.devthoughts.jsr380.validation.BankAccountNumber;
import pl.devthoughts.jsr380.validation.Bic;
import pl.devthoughts.jsr380.validation.Iban;

@Getter
@Setter
class CreateBankAccountRequest {

    @Iban
    private String iban;

    @Bic
    private String bic;

    @BankAccountNumber
    private String bankAccountNumber;

}
