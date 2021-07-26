package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.config.exception.InvalidStateObjectException;
import br.com.zupacademy.eduardo.casadocodigo.config.validacao.ErroPadraoDTO;
import br.com.zupacademy.eduardo.casadocodigo.controller.dto.ClienteDTO;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.ClienteForm;
import br.com.zupacademy.eduardo.casadocodigo.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> insert(@RequestBody @Valid ClienteForm form, HttpServletRequest request) {
        try {
            Cliente cliente = form.converter(manager);
            manager.persist(cliente);

            return ResponseEntity.ok(new ClienteDTO(cliente));
        } catch (InvalidStateObjectException e) {
            return ResponseEntity
                    .badRequest()
                        .body(new ErroPadraoDTO(HttpStatus.BAD_REQUEST.value(), "Estado e País informados não coincidem !",
                                                request.getRequestURI()));
        }
    }
}
