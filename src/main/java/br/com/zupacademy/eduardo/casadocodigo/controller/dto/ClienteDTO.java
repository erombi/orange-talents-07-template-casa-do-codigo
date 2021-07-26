package br.com.zupacademy.eduardo.casadocodigo.controller.dto;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.ValidEntityEstado;
import br.com.zupacademy.eduardo.casadocodigo.model.Cliente;
import br.com.zupacademy.eduardo.casadocodigo.model.Estado;
import br.com.zupacademy.eduardo.casadocodigo.model.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ValidEntityEstado
public class ClienteDTO {

    private Long id;

    @Deprecated
    public ClienteDTO() { }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ClienteDTO(@NotNull @Valid Cliente cliente) {
        this.id = cliente.getId();
    }

    public Long getId() {
        return id;
    }
}
