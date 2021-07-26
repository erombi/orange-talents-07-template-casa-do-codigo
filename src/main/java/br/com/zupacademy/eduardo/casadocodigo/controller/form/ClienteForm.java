package br.com.zupacademy.eduardo.casadocodigo.controller.form;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.Documento;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.DuplicatedValue;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.ValidEntityEstado;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.ExistsId;
import br.com.zupacademy.eduardo.casadocodigo.model.Cliente;
import br.com.zupacademy.eduardo.casadocodigo.model.Estado;
import br.com.zupacademy.eduardo.casadocodigo.model.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ValidEntityEstado
public class ClienteForm {

    @NotBlank
    @Email
    @DuplicatedValue(clazz = Cliente.class, field = "email")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @Documento
    @DuplicatedValue(clazz = Cliente.class, field = "documento")
    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @ExistsId(clazz = Pais.class, field = "id")
    @NotNull
    private Integer paisId;

    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @Deprecated
    public ClienteForm() { }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ClienteForm(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
                        @Documento @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
                        @NotBlank String cidade, @NotNull Integer paisId, Long estadoId, @NotBlank String telefone,
                        @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public Cliente converter(EntityManager manager) {
        Pais pais = manager.find(Pais.class, this.paisId);
        Estado estado = null;

        if (estadoId != null) {
            estado = manager.find(Estado.class, estadoId);
        }

        Cliente cliente = new Cliente(this.email, this.nome, this.sobrenome, this.documento, this.endereco, this.complemento,
                this.cidade, pais, this.telefone, this.cep);

        if (estado != null) {
            cliente.setEstado(estado);
        }

        return cliente;
    }
}
