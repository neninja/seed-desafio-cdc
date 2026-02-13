package app.biblioteca_dev_eficiente.response;

import java.time.Instant;

import app.biblioteca_dev_eficiente.model.Autor;

public record AutorResponse(
    Long id, String nome, String email, String descricao, Instant registradoEm) {
  public AutorResponse(Autor autor) {
    this(
        autor.getId(),
        autor.getNome(),
        autor.getEmail(),
        autor.getDescricao(),
        autor.getRegistradoEm());
  }
}
