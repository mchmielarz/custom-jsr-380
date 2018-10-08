package pl.devthoughts.jsr380.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BankAccountNumberValidator implements ConstraintValidator<BankAccountNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null;
    }

}
