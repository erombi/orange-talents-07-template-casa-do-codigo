package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.validator.UniqueEmailAutorValidator;
import br.com.zupacademy.eduardo.casadocodigo.model.Autor;
import br.com.zupacademy.eduardo.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.AutorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @Autowired
    private UniqueEmailAutorValidator validatorEmail;

    @InitBinder
    public void initAutorValidator(WebDataBinder binder) {
        binder.addValidators(validatorEmail);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid AutorForm form) {
        Autor autor = form.converter();

        repository.save(autor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
