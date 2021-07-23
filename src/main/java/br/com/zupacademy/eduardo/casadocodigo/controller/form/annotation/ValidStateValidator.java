package br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.EstadoForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Estado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidStateValidator implements ConstraintValidator<ValidState, EstadoForm> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean isValid(EstadoForm form, ConstraintValidatorContext constraintValidatorContext) {
        if (form.getNome() == null || form.getNome().isBlank() || form.getPaisId() == null) {
            return false;
        }

        TypedQuery<Estado> typedQuery = manager.createQuery("SELECT e FROM Estado e where e.nome = :nome and e.pais.id = :paisId", Estado.class);
        typedQuery.setParameter("nome", form.getNome());
        typedQuery.setParameter("paisId", form.getPaisId());

        List<Estado> list = typedQuery.getResultList();

        return list.isEmpty();
    }
}
