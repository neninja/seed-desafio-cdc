package app.biblioteca_dev_eficiente.dto;

import java.time.Instant;

import app.biblioteca_dev_eficiente.model.Autor;

public record AutorResponseDto(Long id, String nome, String email, String descricao, Instant registradoEm) {
  public AutorResponseDto(Autor autor) {
    this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDescricao(), autor.getRegistradoEm());
  }
}