package app.biblioteca_dev_eficiente.request;

import jakarta.validation.constraints.NotBlank;

import app.biblioteca_dev_eficiente.model.Categoria;

public record CreateCategoriaRequest(@NotBlank String nome) {

  public Categoria toModel() {
    return new Categoria(nome);
  }
}
