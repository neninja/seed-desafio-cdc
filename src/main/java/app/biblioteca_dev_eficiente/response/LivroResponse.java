package app.biblioteca_dev_eficiente.response;

import java.math.BigDecimal;
import java.time.Instant;

import app.biblioteca_dev_eficiente.model.Livro;

public record LivroResponse(
    Long id,
    String titulo,
    String resumo,
    String sumario,
    BigDecimal preco,
    Integer numeroPaginas,
    String isbn,
    Instant publicadoEm,
    Long categoriaId,
    Long autorId) {

  public LivroResponse(Livro livro) {
    this(
        livro.getId(),
        livro.getTitulo(),
        livro.getResumo(),
        livro.getSumario(),
        livro.getPreco(),
        livro.getNumeroPaginas(),
        livro.getIsbn(),
        livro.getPublicadoEm(),
        livro.getCategoria().getId(),
        livro.getAutor().getId());
  }
}