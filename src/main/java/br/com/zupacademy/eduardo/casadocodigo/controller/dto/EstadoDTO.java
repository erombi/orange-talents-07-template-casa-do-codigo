package br.com.zupacademy.eduardo.casadocodigo.controller.dto;

import br.com.zupacademy.eduardo.casadocodigo.model.Estado;

public class EstadoDTO {

    private Long id;
    private String nome;

    @Deprecated
    public EstadoDTO() {
    }

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
