package br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidStateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidState {

    String message() default "Estado inválido !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
