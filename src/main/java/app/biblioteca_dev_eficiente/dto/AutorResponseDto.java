package app.biblioteca_dev_eficiente.dto;

import app.biblioteca_dev_eficiente.model.Autor;

public record AutorResponseDto(Long id, String nome) {
  public AutorResponseDto(Autor autor) {
    this(autor.getId(), autor.getNome());
  }
}
