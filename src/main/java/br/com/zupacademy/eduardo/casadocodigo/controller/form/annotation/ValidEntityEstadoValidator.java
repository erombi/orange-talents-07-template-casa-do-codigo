package br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.ClienteForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Estado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidEntityEstadoValidator implements ConstraintValidator<ValidEntityEstado, ClienteForm> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean isValid(ClienteForm form, ConstraintValidatorContext constraintValidatorContext) {
        TypedQuery<Estado> query = manager.createQuery("SELECT e FROM Estado e WHERE e.pais.id = :paisId", Estado.class);
        query.setParameter("paisId", form.getPaisId());
        List<Estado> estados = query.getResultList();

        return (estados.isEmpty() && (form.getEstadoId() == null)) || ((!estados.isEmpty()) && (form.getEstadoId() != null));
    }
}
