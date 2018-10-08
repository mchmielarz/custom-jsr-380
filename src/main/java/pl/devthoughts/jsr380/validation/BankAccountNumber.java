package pl.devthoughts.jsr380.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BankAccountNumberValidator.class)
public @interface BankAccountNumber {

    String message() default "{bank-account-number.constraint}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
