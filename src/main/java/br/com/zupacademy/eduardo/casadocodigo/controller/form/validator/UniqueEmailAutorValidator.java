package br.com.zupacademy.eduardo.casadocodigo.controller.form.validator;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.AutorForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Autor;
import br.com.zupacademy.eduardo.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UniqueEmailAutorValidator implements Validator {

    @Autowired
    private AutorRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        String email = ((AutorForm) o).getEmail();

        Optional<Autor> optionalAutor = repository.findByEmail(email);

        if (optionalAutor.isPresent()) {
            errors.rejectValue("email", null,"E-mail informado já cadastrado !");
        }
    }
}
