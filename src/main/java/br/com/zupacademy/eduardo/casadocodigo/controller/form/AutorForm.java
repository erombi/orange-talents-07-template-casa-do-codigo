package br.com.zupacademy.eduardo.casadocodigo.controller.form;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.DuplicatedValue;
import br.com.zupacademy.eduardo.casadocodigo.model.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutorForm {

    @NotBlank
    private String nome;

    @DuplicatedValue(field = "email", clazz = Autor.class)
    @Email
    @NotBlank
    private String email;

    @Length(max = 400)
    @NotBlank
    private String descricao;

    public AutorForm(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Length(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converter() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return this.email;
    }
}
