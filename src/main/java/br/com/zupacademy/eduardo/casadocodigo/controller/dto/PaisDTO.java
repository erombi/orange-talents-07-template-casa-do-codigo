package br.com.zupacademy.eduardo.casadocodigo.controller.dto;

import br.com.zupacademy.eduardo.casadocodigo.model.Pais;

public class PaisDTO {

    private Integer id;
    private String nome;

    @Deprecated
    public PaisDTO() {
    }

    public PaisDTO(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
