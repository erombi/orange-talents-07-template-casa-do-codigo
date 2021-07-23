package br.com.zupacademy.eduardo.casadocodigo.controller.dto;

import br.com.zupacademy.eduardo.casadocodigo.repository.projection.DetalheLivroProjection;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LivroDetalheDTO {

    private Long id;
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroPaginas;
    private String isbn;
    private AutorDTO autor;

    @Deprecated
    public LivroDetalheDTO() {    }

    public LivroDetalheDTO(@NotNull DetalheLivroProjection livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.autor = new AutorDTO(livro.getAutorNome(), livro.getAutorDescricao());
    }

    @Override
    public String toString() {
        return "LivroDetalheDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", numeroPaginas=" + numeroPaginas +
                ", isbn='" + isbn + '\'' +
                ", autor=" + autor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public AutorDTO getAutor() {
        return autor;
    }
}
