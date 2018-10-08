package pl.devthoughts.jsr380.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BicValidator.class)
public @interface Bic {

    String message() default "{bic.constraint}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
