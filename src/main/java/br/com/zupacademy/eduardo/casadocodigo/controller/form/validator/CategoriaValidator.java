package br.com.zupacademy.eduardo.casadocodigo.controller.form.validator;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.CategoriaForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Categoria;
import br.com.zupacademy.eduardo.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CategoriaValidator implements Validator {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Optional<Categoria> optionalCategoria = repository.findByNome(((CategoriaForm) o).getNome());

        if (optionalCategoria.isPresent()) {
            errors.rejectValue("nome", null, "O nome da categoria informado já foi cadastrado anteriormente !");
        }
    }
}
