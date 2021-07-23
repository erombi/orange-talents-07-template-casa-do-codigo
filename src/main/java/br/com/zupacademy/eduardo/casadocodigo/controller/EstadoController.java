package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.EstadoForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Estado;
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
@RequestMapping("/estados")
public class EstadoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> insert(@RequestBody @Valid EstadoForm form) {
        Estado estado = form.converter(manager);
        manager.persist(estado);

        return ResponseEntity.ok().build();
    }

}
