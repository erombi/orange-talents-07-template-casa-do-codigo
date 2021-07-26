package br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidEntityEstadoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEntityEstado {

    String message() default "Existem estados para o País selecionado, por favor indique o correspondente !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
