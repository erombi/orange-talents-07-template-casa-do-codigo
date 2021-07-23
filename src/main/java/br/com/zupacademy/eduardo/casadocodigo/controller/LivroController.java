package br.com.zupacademy.eduardo.casadocodigo.controller;

import br.com.zupacademy.eduardo.casadocodigo.config.exception.ResourceNotFoundException;
import br.com.zupacademy.eduardo.casadocodigo.controller.dto.ItemListaLivroDTO;
import br.com.zupacademy.eduardo.casadocodigo.controller.dto.LivroDetalheDTO;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.LivroForm;
import br.com.zupacademy.eduardo.casadocodigo.model.ItemListaLivro;
import br.com.zupacademy.eduardo.casadocodigo.model.Livro;
import br.com.zupacademy.eduardo.casadocodigo.repository.projection.DetalheLivroProjection;
import br.com.zupacademy.eduardo.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    public EntityManager manager;

    @Autowired
    public LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> insert(@RequestBody @Valid LivroForm form) {
        Livro livro = form.converter(manager);
        manager.persist(livro);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<ItemListaLivroDTO>> findAll() {
        TypedQuery<ItemListaLivro> query = manager.createQuery("SELECT l FROM Livro l", ItemListaLivro.class);
        List<ItemListaLivroDTO> livroDTOs = query.getResultList()
                                                        .stream()
                                                        .map(ItemListaLivroDTO::new)
                                                        .collect(Collectors.toList());
        return ResponseEntity.ok(livroDTOs);
    }

    @GetMapping(value = "/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<LivroDetalheDTO> findById(@PathVariable Long id, HttpServletRequest request) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro não encontrado !", request.getRequestURI());
        }
        DetalheLivroProjection detalheLivroProjection = livroRepository.detalheLivro(id);

        return ResponseEntity.ok(new LivroDetalheDTO(detalheLivroProjection));
    }


}
