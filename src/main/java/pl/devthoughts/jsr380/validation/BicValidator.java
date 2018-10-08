package pl.devthoughts.jsr380.validation;

import io.vavr.control.Try;

import lombok.extern.slf4j.Slf4j;

import org.iban4j.BicUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class BicValidator implements ConstraintValidator<Bic, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return Try.of(() -> {
            BicUtil.validate(value);
            return true;
        })
                  .onFailure(exc -> log.error("Invalid BIC code provided: {}", value, exc))
                  .getOrElseGet(exc -> false);
    }

}
