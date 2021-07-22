package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.config.validacao.ErroDeFormularioDTO;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.LivroForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    public EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> insert(@RequestBody @Valid LivroForm form) {
            Livro livro = form.converter(manager);
            manager.persist(livro);

            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
