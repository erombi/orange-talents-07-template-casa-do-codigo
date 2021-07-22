package br.com.zupacademy.eduardo.casadocodigo.controller.form;

import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.DuplicatedValue;
import br.com.zupacademy.eduardo.casadocodigo.controller.form.annotation.ExistsId;
import br.com.zupacademy.eduardo.casadocodigo.model.Autor;
import br.com.zupacademy.eduardo.casadocodigo.model.Categoria;
import br.com.zupacademy.eduardo.casadocodigo.model.Livro;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroForm {

    @DuplicatedValue(clazz = Livro.class, field = "titulo")
    @NotBlank
    private String titulo;

    @Length(max = 500)
    @NotBlank
    private String resumo;

    private String sumario;

    @Min(20)
    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroPaginas;

    @DuplicatedValue(clazz = Livro.class, field = "isbn")
    @NotBlank
    private String isbn;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @ExistsId(clazz = Categoria.class, field = "id")
    @NotNull
    private Long categoriaId;

    @ExistsId(clazz = Autor.class, field = "id")
    @NotNull
    private Long autorId;

    @Deprecated
    public LivroForm() { }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LivroForm(@NotBlank String titulo, @Length(max = 500) @NotBlank String resumo, String sumario,
                     @Size(min = 20) @NotNull @Positive BigDecimal preco,  @NotNull Integer numeroPaginas,
                     @NotBlank String isbn, @Future LocalDate dataPublicacao, @NotNull Long categoriaId,
                     @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }


    public Livro converter(EntityManager manager) {
        Autor autorEntity = manager.find(Autor.class, this.autorId);
        Categoria categoriaEntity = manager.find(Categoria.class, this.categoriaId);

        return new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroPaginas, this.isbn,
                            this.dataPublicacao, categoriaEntity, autorEntity);
    }

}
