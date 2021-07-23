package br.com.zupacademy.eduardo.casadocodigo.controller.form;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.DuplicatedValue;
import br.com.zupacademy.eduardo.casadocodigo.model.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PaisForm {

    @DuplicatedValue(clazz = Pais.class, field = "nome")
    @NotBlank
    private String nome;

    @Deprecated
    public PaisForm() { }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PaisForm(@NotBlank String nome) {
        this.nome = nome;
    }


    public Pais converter() {
        return new Pais(this.nome);
    }
}
