package br.com.zupacademy.eduardo.casadocodigo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_livro")
public class Livro implements ItemListaLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String titulo;

    @Length(max = 500)
    @NotBlank
    @Column(length = 500)
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String sumario;

    @Min(20)
    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroPaginas;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @Future
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo, @Length(max = 500) @NotBlank String resumo, String sumario,
                    @Size(min = 20) @NotNull @Positive BigDecimal preco,  @NotNull Integer numeroPaginas,
                    @NotBlank String isbn, @Future LocalDate dataPublicacao, @NotNull Categoria categoria,
                    @NotNull Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
