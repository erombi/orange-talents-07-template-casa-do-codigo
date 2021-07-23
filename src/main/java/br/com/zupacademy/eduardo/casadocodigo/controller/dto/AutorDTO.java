package br.com.zupacademy.eduardo.casadocodigo.controller.dto;

import br.com.zupacademy.eduardo.casadocodigo.model.Autor;

public class AutorDTO {

    private String nome;
    private String descricao;

    @Deprecated
    public AutorDTO() {    }

    public AutorDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public AutorDTO(Autor autor) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
