package br.com.zupacademy.eduardo.casadocodigo.repository;

import br.com.zupacademy.eduardo.casadocodigo.model.Livro;
import br.com.zupacademy.eduardo.casadocodigo.repository.projection.DetalheLivroProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(
            value = "SELECT l.id, l.titulo, l.resumo, l.sumario, l.preco, l.numero_paginas numeroPaginas, l.isbn, " +
                        "a.nome autorNome, a.descricao autorDescricao " +
                    "FROM tb_livro l INNER JOIN tb_autor a ON (a.id = l.autor_id) " +
                    "WHERE l.id = :id",
            nativeQuery = true
    )
//    @Query(
//            "select l, l.autor.nome, l.autor.descricao from Livro l where l.id = :id"
//    )
    DetalheLivroProjection detalheLivro(Long id);
}
