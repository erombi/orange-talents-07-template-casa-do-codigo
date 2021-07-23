package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.PaisForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Pais;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> insert(@RequestBody @Valid PaisForm form) {
        Pais pais =  form.converter();
        manager.persist(pais);

        return ResponseEntity.ok().build();
    }
}
