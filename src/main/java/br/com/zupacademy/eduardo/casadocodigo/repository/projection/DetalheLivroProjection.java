package br.com.zupacademy.eduardo.casadocodigo.repository.projection;

import java.math.BigDecimal;

public interface DetalheLivroProjection {

    Long getId();
    String getTitulo();
    String getResumo();
    String getSumario();
    BigDecimal getPreco();
    Integer getNumeroPaginas();
    String getIsbn();
    String getAutorNome();
    String getAutorDescricao();
}
