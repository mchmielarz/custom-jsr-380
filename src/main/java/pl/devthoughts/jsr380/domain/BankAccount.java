package pl.devthoughts.jsr380.domain;

import lombok.Data;

import pl.devthoughts.jsr380.domain.dto.BankAccountData;
import pl.devthoughts.jsr380.validation.BankAccountNumber;
import pl.devthoughts.jsr380.validation.Bic;
import pl.devthoughts.jsr380.validation.Iban;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
class BankAccount {

    @Id
    private long id;

    @Column
    @Iban
    private String iban;

    @Column
    @Bic
    private String bic;

    @Column
    @BankAccountNumber
    private String bankAccountNumber;

    BankAccount(BankAccountData data) {
        this.iban = data.getIban();
        this.bic = data.getBic();
        this.bankAccountNumber = data.getBankAccountnumber();
    }
}
