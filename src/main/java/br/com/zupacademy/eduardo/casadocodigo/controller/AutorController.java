package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.model.Autor;
import br.com.zupacademy.eduardo.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.AutorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid AutorForm form) {
        Autor autor = form.converter();

        repository.save(autor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
