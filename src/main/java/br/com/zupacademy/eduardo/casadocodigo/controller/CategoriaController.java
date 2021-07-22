package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.CategoriaForm;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.validator.CategoriaValidator;
import br.com.zupacademy.eduardo.casadocodigo.model.Categoria;
import br.com.zupacademy.eduardo.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaValidator categoriaValidator;

    @InitBinder
    public void initCategoriaBinder(WebDataBinder binder) {
        binder.addValidators(categoriaValidator);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid CategoriaForm form) {
        Categoria categoria = form.converter();

        repository.save(categoria);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
