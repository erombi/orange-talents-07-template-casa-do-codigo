package br.com.zupacademy.eduardo.casadocodigo.controller.dto;

import br.com.zupacademy.eduardo.casadocodigo.model.ItemListaLivro;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ItemListaLivroDTO {

    private Long id;
    private String titulo;

    @Deprecated
    public ItemListaLivroDTO() {
    }

    public ItemListaLivroDTO(@NotNull @Valid ItemListaLivro itemLista) {
        this.id = itemLista.getId();
        this.titulo = itemLista.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
