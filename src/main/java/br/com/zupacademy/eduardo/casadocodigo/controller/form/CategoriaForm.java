package br.com.zupacademy.eduardo.casadocodigo.controller.form;

import br.com.zupacademy.eduardo.casadocodigo.model.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank
    private String nome;

    @Deprecated
    public CategoriaForm() {  }

    public CategoriaForm(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria converter() {
        return new Categoria(this.nome);
    }

    public String getNome() {
        return this.nome;
    }
}
