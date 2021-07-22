package br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    @PersistenceContext
    private EntityManager manager;

    private Class<?> clazz;
    private String field;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        this.clazz = constraintAnnotation.clazz();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("SELECT 1 FROM " + clazz.getName() + " WHERE " + field + " =  :value");
        query.setParameter("value", value);
        List list = query.getResultList();

        return !list.isEmpty();
    }
}
