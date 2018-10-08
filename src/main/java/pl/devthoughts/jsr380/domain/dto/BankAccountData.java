package pl.devthoughts.jsr380.domain.dto;

import lombok.Value;

@Value
public class BankAccountData {

    private final String iban;
    private final String bic;
    private final String bankAccountnumber;

}
