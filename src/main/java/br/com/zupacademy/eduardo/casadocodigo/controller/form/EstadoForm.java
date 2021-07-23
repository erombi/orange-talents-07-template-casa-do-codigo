package br.com.zupacademy.eduardo.casadocodigo.controller.form;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.ExistsId;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.ValidState;
import br.com.zupacademy.eduardo.casadocodigo.model.Estado;
import br.com.zupacademy.eduardo.casadocodigo.model.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ValidState
public class EstadoForm {

    @NotBlank
    private String nome;

    @NotNull
    @ExistsId(clazz = Pais.class, field = "id")
    private Integer paisId;

    @Deprecated
    public EstadoForm() { }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EstadoForm(String nome, Integer paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public String getNome() {
        return nome;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public Estado converter(EntityManager manager) {
        TypedQuery<Pais> query = manager.createQuery("SELECT p from Pais p where p.id = :paisId", Pais.class);
        Pais pais = query.setParameter("paisId", this.paisId).getSingleResult();

        return new Estado(this.nome, pais);
    }
}
