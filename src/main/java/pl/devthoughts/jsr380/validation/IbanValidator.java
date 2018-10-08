package pl.devthoughts.jsr380.validation;

import io.vavr.control.Try;

import lombok.extern.slf4j.Slf4j;

import org.iban4j.IbanFormat;
import org.iban4j.IbanUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class IbanValidator implements ConstraintValidator<Iban, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return Try.of(() -> {
            if (value.contains(" ")) {
                IbanUtil.validate(value, IbanFormat.Default);
            } else {
                IbanUtil.validate(value);
            }
            return true;
        })
                  .onFailure(exc -> log.error("Invalid IBAN provided: {}", value, exc))
                  .getOrElseGet(exc -> false);
    }

}
